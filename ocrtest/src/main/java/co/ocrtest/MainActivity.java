package co.ocrtest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
    private static String IMG_PATH = getSDPath() + java.io.File.separator
            + "ocrtest";
    private static final int PHOTO_CAPTURE = 0x11;// 拍照
    private static final int PHOTO_RESULT = 0x12;// 结果
    static final String TESSBASE_PATH = "/storage/emulated/0/tesseract/";
    //识别语言英文
    static final String DEFAULT_LANGUAGE = "eng";
    private static String LANGUAGE = "eng";
    private ImageView iv;
    private TextView tv;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv= (ImageView) findViewById(R.id.iv_image);
        tv= (TextView) findViewById(R.id.tv);
        radioGroup= (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_en:
                        LANGUAGE = "eng";
                        break;
                    case R.id.rb_ch:
                        LANGUAGE = "chi_sim";
                        break;
                    case R.id.rb_ara:
                        LANGUAGE = "ara";
                        break;
                }
                tv.setText(LANGUAGE);
            }
        });
    }
    public void onClick(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(IMG_PATH, "temp.jpg")));
        startActivityForResult(intent, PHOTO_CAPTURE);
    }

    /**
     * 获取sd卡的路径
     *
     * @return 路径的字符串
     */
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取外存目录
        }
        return sdDir.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_CANCELED)
            return;

        if (requestCode == PHOTO_CAPTURE) {
            startPhotoCrop(Uri.fromFile(new File(IMG_PATH, "temp.jpg")));
        }
        // 处理结果
        if (requestCode == PHOTO_RESULT) {
            final Bitmap bitmapSelected = decodeUriAsBitmap(Uri.fromFile(new File(IMG_PATH,
                    "temp_cropped.jpg")));
            //final Bitmap bitmapSelected=BitmapFactory.decodeResource(getResources(),R.drawable.english);
            // 显示选择的图片
            iv.setImageBitmap(bitmapSelected);

            // 新线程来处理识别
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TessBaseAPI baseApi = new TessBaseAPI();
                    //初始化OCR的训练数据路径与语言
                    baseApi.init(TESSBASE_PATH, LANGUAGE);
                    //设置识别模式
                    baseApi.setPageSegMode(TessBaseAPI.PageSegMode.PSM_SINGLE_LINE);
                    baseApi.setImage(bitmapSelected);
                    textResult=baseApi.getUTF8Text();
                    baseApi.clear();
                    baseApi.end();


                    Message msg2 = new Message();
                    msg2.what = 1;
                    myHandler.sendMessage(msg2);
                }

            }).start();
        }
    }
    /**
     * 调用系统图片编辑进行裁剪
     */
    public void startPhotoCrop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(IMG_PATH, "temp_cropped.jpg")));
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, PHOTO_RESULT);
    }

    /**
     * 根据URI获取位图
     *
     * @param uri
     * @return 对应的位图
     */
    private Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver()
                    .openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }
    private static String textResult;
    // 该handler用于处理修改结果的任务
    public Handler myHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if ("".equals(textResult)||textResult==null)
                        tv.setText("识别失败");
                    else
                        tv.setText(textResult);
                    break;
            }
            super.handleMessage(msg);
        }

    };

}
