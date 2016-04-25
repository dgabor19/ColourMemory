package com.accedo.colourmemory.utils;

/**
 * Util class to convert some stuffs
 * Created by gabordudas on 20/08/14.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.util.TypedValue;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Converter {
    private static final String TAG = Converter.class.getSimpleName();

    /**
     * Converts pixel to dp
     *
     * @param context
     * @param pixels
     * @return
     */
    public static float convertPixelsToDp(final Context context, final float pixels) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixels, context.getResources().getDisplayMetrics());
    }

    /**
     * Converts dp to pixel
     *
     * @param context
     * @param dp
     * @return
     */
    public static float convertDpToPixels(final Context context, final float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    /**
     * Converts sp to pixel
     *
     * @param context
     * @param sp
     * @return
     */
    public static int convertSpToPixels(final Context context, final float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    /**
     * Calculate the text size in pixel
     *
     * @param context
     * @param text
     * @return
     */
    public static float getTextWidth(final Context context, final String text) {
        Paint paint = new Paint();
        TextView textView = new TextView(context);
        textView.setText(text);
        paint.setTextSize(textView.getTextSize());

        float width = paint.measureText(text);

        return width;
    }

    /**
     * Calculate the text size in pixel
     *
     * @param text
     * @return
     */
    public static float getTextWidth(final String text, final Paint paint) {
        float width = paint.measureText(text);

        return width;
    }

    /**
     * Calculates the textview's text size
     *
     * @param textView
     * @return
     */
    public static float getTextWidth(final TextView textView) {
        Paint paint = new Paint();
        paint.setTextSize(textView.getTextSize());

        float width = paint.measureText((String) textView.getText());


        return width;
    }

    public static String computeMD5Hash(String password) {
        String result = "";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer MD5Hash = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                MD5Hash.append(h);
            }

            result = MD5Hash.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static byte[] getBytesFromInputStream(InputStream is) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] buffer = new byte[0xFFFF];

            for (int len; (len = is.read(buffer)) != -1; ) {
                os.write(buffer, 0, len);
            }

            os.flush();

            return os.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    public static byte[] getBitmapByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    public static byte[] getUriByteArray(Context context, Uri uri) {
        InputStream iStream;
        byte[] inputData = null;
        try {
            iStream = context.getContentResolver().openInputStream(uri);

            inputData = getBytes(iStream);

            if (iStream != null) {
                iStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return inputData;
    }

    public static byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }
}
