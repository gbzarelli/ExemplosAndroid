/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpdev.hardware.camera;

import android.app.Activity;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;

/**
 *
 * @author Guilherme Biff Zarelli
 */
public class CameraController implements SurfaceHolder.Callback {

    private android.hardware.Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private boolean previewing = false;

    public CameraController(Activity atividade, int surfaceView) {
        this.surfaceView = (SurfaceView) atividade.findViewById(surfaceView);
        surfaceHolder = this.surfaceView.getHolder();
        surfaceHolder.addCallback(this);
    }

    /**
     * Isto é chamado imediatamente após o SurfaceHolder ser criado.
     *
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera = android.hardware.Camera.open();
    }

    /**
     * Isto é chamado imediatamente após as alterações estruturais (o formato ou
     * tamanho) foram feitos ao SurfaceHolder.
     */
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (previewing) {
            pararVisualizacao();
        }

        if (camera != null) {
            try {
                camera.setPreviewDisplay(surfaceHolder);
                iniciarVisualizacao();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Isto é chamado imediatamente antes do SurfaceHolder ser destruído.
     *
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        pararVisualizacao();
        camera.release();
        camera = null;
    }

    public void tirarFoto(Camera.ShutterCallback shutter, Camera.PictureCallback raw, Camera.PictureCallback jpeg) {
        camera.takePicture(shutter, raw, jpeg);
    }

    public void iniciarVisualizacao() {
        previewing = true;
        camera.startPreview();
    }

    public void pararVisualizacao() {
        camera.stopPreview();
        previewing = false;
    }

    public Camera getCameraControler() {
        return camera;
    }
}
