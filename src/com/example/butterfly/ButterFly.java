package com.example.butterfly;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class ButterFly extends Activity {
	
	//��¼����ImageView��ǰ��λ��
    private float currentX = 0;
    private float currentY = 30;
    //��¼����ImageView��һ��λ�õ�����
    float nextX = 0;
    float nextY = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final ImageView imageView = (ImageView)findViewById(R.id.butterfly);
        final Handler handler = new Handler(){
        	
        	@Override
        	public void handleMessage(Message msg) {
        		// TODO Auto-generated method stub
        		if (msg.what == 0x123)
        		{
        			//������һֱ���ҷ�
        			if (nextX > 320)
        			{
        				currentX = nextX = 0;
        			}
        			else{
        				nextX += 8;
        			}
        			//�����Ͽ����������
        			nextY = currentY + (float)(Math.random() * 10 - 5);
        			//������ʾ������ImageView����λ�Ƹı�
        			TranslateAnimation anim = new TranslateAnimation(currentX, nextX, currentY, nextY);
        			currentX = nextX;
        			currentY = nextY;
        			anim.setDuration(200);
        			imageView.startAnimation(anim);  //��ʼλ�ƶ���
        		}
        	}
        	
        };
        
        final AnimationDrawable butterfly = (AnimationDrawable)imageView.getBackground();
        
        imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				butterfly.start(); //��ʼ���ź���������֡����
				//ͨ�����ƿ�����ÿ0.2������һ��TranslateAnimation����
				new Timer().schedule(new TimerTask() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						handler.sendEmptyMessage(0x123);
					}
				}, 0, 200);
			}
		});
        
    }

}
