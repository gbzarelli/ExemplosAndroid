package www.szxiaobing;





import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.*;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Main extends Activity {
    /** Called when the activity is first created. */
    Button SendButton;
    EditText MsgEdit;
    TextView showMsg;
    BluetoothAdapter BTadapter;
    ProgressDialog SearchDialog;
    BluetoothDevice device;
    BluetoothSocket socket;
    String handleMsg;
    String revMsg;
    Handler msgHandler = new Handler()
    {
    	public void handleMessage(Message msg)
    	{
    		super.handleMessage(msg);
    		switch (msg.what){
    			case MESSAGE_REV:
    	    		showMsg.append(revMsg);
    			case MESSAGE_SEND:
    				showMsg.append(handleMsg);
    				
    				
    				
    			
    		}
    			
 
    	}
    };
    BufferedReader MsgReader;
    OutputStream MsgWriter;
    private static final String TAG="Bluetooth";
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_DISCOVERY = 2;
    private static final int MESSAGE_REV = 1;
    private static final int MESSAGE_SEND = 2;
	private Runnable MsgRun = new Runnable() {
		public void run()
		{
			
			
			try {
				
				
					while ((revMsg=MsgReader.readLine())!= null)
				{
					Message msg =new Message();
					msg.what=MESSAGE_REV;
					msgHandler.sendMessage(msg);
					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SendButton = (Button)findViewById(R.id.button);
        MsgEdit = (EditText) findViewById(R.id.edit);
        showMsg= (TextView) findViewById(R.id.text);
        BTadapter= BluetoothAdapter.getDefaultAdapter();
        if (!BTadapter.isEnabled()) //isEnabled这个方法检查是否开启了蓝牙
        {   
        	   Intent enableBtIntent= new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);    
        	   startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        //开始搜索
		Intent discoverrs = new Intent(this, DiscoveryActivity.class);
		startActivityForResult(discoverrs, REQUEST_DISCOVERY);

	}
	 public void onActivityResult(int requestCode, int resultCode, Intent data) {
		 switch (requestCode) {
		 case REQUEST_ENABLE_BT:
	            if (resultCode == Activity.RESULT_OK) {
	            	Toast.makeText(this, "成功打开蓝牙", Toast.LENGTH_SHORT).show();
	                
	            } else {
	               
	                Log.d(TAG, "BT 没开");
	                Toast.makeText(this, "不能打开蓝牙,程序即将关闭", Toast.LENGTH_SHORT).show();
	                finish();
	            }
		 case REQUEST_DISCOVERY:
	            // When DeviceListActivity returns with a device to connect
			 	device = data.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
			 	connect(device);
	            break;
		 
		 }
		 return;
	 }
	 public boolean onKeydown(int keyCode ,KeyEvent event){
		 switch (keyCode)
		 {
		 case KeyEvent.KEYCODE_BACK:
			 finish();
			 try {
				if (socket!=null) {socket.close();
				Toast.makeText(this, "成功关闭连接，退出程序。", Toast.LENGTH_SHORT).show();}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 return false;
		 }
		protected void connect(BluetoothDevice device) {
			
			try {

				socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
				//连接
				socket.connect();
				Toast.makeText(this, socket.toString(), Toast.LENGTH_SHORT).show();
				if (socket!=null) 
					{
					Toast.makeText(this, "成功连接！", Toast.LENGTH_SHORT).show();
					MsgReader= new BufferedReader (new InputStreamReader(socket.getInputStream()));
					MsgWriter= socket.getOutputStream();
					handleMsg="ready for the remote message!"+"\n";
					//连接成功的时候发送提示成功消息。
					Message msg=new Message();
					msg.what=MESSAGE_SEND;
					msgHandler.sendMessage(msg);
					new Thread (MsgRun).start();
					}
				SendButton.setOnClickListener(new OnClickListener()
				{
					public void onClick(View v)
					{
						
						String yeas=MsgEdit.getText().toString();
						byte[] msgBuffer = yeas.getBytes();
						handleMsg=MsgEdit.getText().toString()+"\n";
						try {
							MsgWriter.write(msgBuffer);
							MsgWriter.flush();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Message msg=new Message();
						msg.what=MESSAGE_SEND;
						msgHandler.sendMessage(msg);
						
					}
				});
			} catch (IOException e) {
				Log.e(TAG, "", e);
			} 
		}


}
