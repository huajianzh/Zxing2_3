package com.mct.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.mct.zxing2_3.R;
import com.zxing.activity.CaptureActivity;
import com.zxing.encode.QRCodeEncoder;

public class MainActivity extends Activity implements OnClickListener {
	private TextView tvResult;
	private EditText etContent;
	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvResult = (TextView) findViewById(R.id.tv_result);
		etContent = (EditText) findViewById(R.id.et_content);
		iv = (ImageView) findViewById(R.id.m_image);
		findViewById(R.id.btn_scan).setOnClickListener(this);
		findViewById(R.id.btn_create).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_scan:
			toScan();
			break;
		case R.id.btn_create:
			String content = etContent.getText().toString();
			QRCodeEncoder encoder = new QRCodeEncoder(content, 400);
			try {
				Bitmap bitmap = encoder.encodeAsBitmap();
				iv.setImageBitmap(bitmap);
			} catch (WriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

	}

	private void toScan() {
		Intent intent = new Intent(this, CaptureActivity.class);
		startActivityForResult(intent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == RESULT_OK) {
			String result = data.getStringExtra("result");
			tvResult.setText(result);
		}
	}

}
