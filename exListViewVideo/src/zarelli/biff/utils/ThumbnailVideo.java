/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zarelli.biff.utils;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import java.io.File;

/**
 *
 * @author guilherme
 */
public class ThumbnailVideo {

    public static Bitmap getThumbnailFromVideo(File file) {
        return getThumbnailFromVideo(file.getAbsolutePath());
    }

    public static Bitmap getThumbnailFromVideo(String file) {
        return ThumbnailUtils.createVideoThumbnail(file,
                MediaStore.Images.Thumbnails.MINI_KIND);
    }
}
