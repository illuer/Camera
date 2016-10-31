package com.yezi.camera;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private ImageView image;
	private File file;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		image = (ImageView) findViewById(R.id.image);
	}

	public void shot(View v) {
		// 创建意图,将action传入
		// 从文件获取Uri
		// 利用意图打开想要获取的界面
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
		Uri fileUri = Uri.fromFile(file);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
		if (file.exists()) {
			Options option = new Options();
			option.inSampleSize = 10;
			// 从文件获取
			Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), option);
			image.setImageBitmap(bitmap);

		}
	}
}
