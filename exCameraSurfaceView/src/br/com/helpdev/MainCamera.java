package br.com.helpdev;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import br.com.helpdev.hardware.camera.CameraController;

public class MainCamera extends Activity implements View.OnClickListener, ShutterCallback, Camera.PictureCallback {

    private CameraController cameraController;
    private boolean emCamera;
    private Button botaoCamera;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        emCamera = true;
        cameraController = new CameraController(this, R.foto.area_view);

        botaoCamera = (Button) findViewById(R.foto.bt_fotografar);
        botaoCamera.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.foto.bt_fotografar:
                if (emCamera) {
                    cameraController.tirarFoto(this, null, this);
                } else {
                    emCamera = true;
                    botaoCamera.setText(R.string.foto_bt_fotografar);
                    cameraController.iniciarVisualizacao();
                }
                break;
        }
    }

    /**
     * Ação do click tirar foto
     */
    public void onShutter() {
        botaoCamera.setText(R.string.foto_bt_camera);
        emCamera = false;
    }

    /**
     * Retorno da ação de tirar foto
     *
     * @param bytes
     * @param camera
     */
    public void onPictureTaken(byte[] bytes, Camera camera) {

        Bitmap foto = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        cameraController.pararVisualizacao();
    }
}
