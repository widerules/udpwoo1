package npu.dce.woo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class UDPWoo extends Activity {

	Button btnSave, btnSendSMS;
	CheckBox checkOwner;
	EditText editGivenName, editMiddleName, editFamilyName, editPhone, editEmail, 
	         editIM, editStreet, editPOBox, editCity, editState, editZipCode, editCountry, 
	         editSNS, editOrg, editNotes;
	RadioButton btnMan, btnWoman;
	Spinner spinPhone, spinEmail, spinIM, spinPostalAddr, spinSNS, spinOrg;
	long contact_id;
	
	public String total;
	
	String strAndroidOwner, strTypes, strGivenName, strMiddleName, strFamilyName, strMan, strWoman, strSpinPhone, 
		   strPhone, strSpinEmail, strEmail, strSpinIM, strIM, strSpinAddr, strStreet, strPOBox, strCity, strState, strZipCode,
	       strCountry, strSpinSNS, strSNS, strSpinOrg, strOrg, strNotes, strDate, strHour, strMin, strSec; 
	
	private ContactDBAdapter mDbHelper;
	SQLiteDatabase db;
	UpdatePacket update = new UpdatePacket();
	
	
	private String array_phone[];
	private String array_email[];
	private String array_im[];
	private String array_postaladdr[];
	private String array_sns[];
	private String array_org[];
	
	private Handler mHandler = new Handler(); 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
 	
    	super.onCreate(icicle);
        setContentView(R.layout.main);
 
        mDbHelper = new ContactDBAdapter(this);        
        mDbHelper.open();   
        
        btnSave = (Button) findViewById(R.id.buttonSave);
        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
        
        editGivenName = (EditText) findViewById(R.id.txtGivenName);
        editMiddleName = (EditText) findViewById(R.id.txtMiddleName);
        editFamilyName = (EditText) findViewById(R.id.txtFamilyName);
        
        btnMan = (RadioButton)findViewById(R.id.SexMan); 
        btnWoman = (RadioButton)findViewById(R.id.SexWoman); 
        
        checkOwner = (CheckBox) findViewById(R.id.owner);
        
        editPhone = (EditText) findViewById(R.id.txtPhone);
        editEmail = (EditText) findViewById(R.id.txtEmail);
        editIM = (EditText) findViewById(R.id.txtIM);
        editStreet =  (EditText) findViewById(R.id.txtStreet);
        editPOBox = (EditText) findViewById(R.id.txtPOBox);
        editCity = (EditText) findViewById(R.id.txtCity);
        editState = (EditText) findViewById(R.id.txtState);
        editZipCode = (EditText) findViewById(R.id.txtZipCode);
        editCountry = (EditText) findViewById(R.id.txtCountry);
        editSNS = (EditText) findViewById(R.id.txtSNS);
        editOrg = (EditText) findViewById(R.id.txtOrg);
        editNotes = (EditText) findViewById(R.id.txtNotes);
        
        //Spinner Phone
        array_phone = new String[5];
        array_phone[0]="Home";
        array_phone[1]="Home Fax";
        array_phone[2]="Mobile";
        array_phone[3]="Work";
        array_phone[4]="Work Fax";
        spinPhone = (Spinner) findViewById(R.id.spinnerPhone);
        ArrayAdapter adapter_phone = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_phone);
        spinPhone.setAdapter(adapter_phone);

        //Spinner Email
        array_email = new String[4];
        array_email[0]="Home";
        array_email[1]="Work";
        array_email[2]="Mobile";
        array_email[3]="Other";
        spinEmail = (Spinner) findViewById(R.id.spinnerEmail);
        ArrayAdapter adapter_email = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_email);
        spinEmail.setAdapter(adapter_email);
        
        //Spinner IM
        array_im = new String[4];
        array_im[0]="Yahoo";
        array_im[1]="Skype";
        array_im[2]="GoogleTalk";
        array_im[3]="Windows Live";
        spinIM = (Spinner) findViewById(R.id.spinnerIM);
        ArrayAdapter adapter_im = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_im);
        spinIM.setAdapter(adapter_im);
        
        //Spinner  Postal Address
        array_postaladdr = new String[2];
        array_postaladdr[0]="Home";
        array_postaladdr[1]="Work";
        spinPostalAddr = (Spinner) findViewById(R.id.spinnerPostalAddress);
        ArrayAdapter adapter_addr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_postaladdr);
        spinPostalAddr.setAdapter(adapter_addr);
        
        //Spinner SNS
        array_sns = new String[6];
        array_sns[0]="Facebook";
        array_sns[1]="Twitter";
        array_sns[2]="Buzz";
        array_sns[3]="Orkut";
        array_sns[4]="Cyworld";
        array_sns[5]="MySpace";
        spinSNS = (Spinner) findViewById(R.id.spinnerSNS);
        ArrayAdapter adapter_sns = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_sns);
        spinSNS.setAdapter(adapter_sns);
        
        //Spinner Organization
        array_org = new String[3];
        array_org[0]="Company";
        array_org[1]="School";
        array_org[2]="Group";
        spinOrg = (Spinner) findViewById(R.id.spinnerOrg);
        ArrayAdapter adapter_org = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_org);
        spinOrg.setAdapter(adapter_org); 
         
        // Not showing soft keyboard
        editGivenName.setInputType(0);
        editMiddleName.setInputType(0);
        editFamilyName.setInputType(0);
        editPhone.setInputType(0);
        editEmail.setInputType(0); 
        editIM.setInputType(0); 
        editStreet.setInputType(0); 
        editPOBox.setInputType(0); 
        editCity.setInputType(0); 
        editState.setInputType(0); 
        editZipCode.setInputType(0); 
        editCountry.setInputType(0); 
        editSNS.setInputType(0); 
        editOrg.setInputType(0); 
        editNotes.setInputType(0); 
              
        
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { 
            
            	Date date = new Date();
            	
            	if (checkOwner.isChecked()){ 
            		strAndroidOwner = "Y";
            		strTypes = "update";
            	}else{
            		strAndroidOwner = "N";
            		strTypes = "";
            	}
            	
            	strGivenName = editGivenName.getText().toString();                 
            	strMiddleName = editMiddleName.getText().toString();  
            	strFamilyName = editFamilyName.getText().toString();  
            	
            	strMan = btnMan.getText().toString(); 
                strWoman = btnWoman.getText().toString();
                
                strSpinPhone = spinPhone.getSelectedItem().toString();
                strPhone = editPhone.getText().toString();  
                
                strSpinEmail = spinEmail.getSelectedItem().toString();
                strEmail = editEmail.getText().toString(); 
                
                strSpinIM = spinIM.getSelectedItem().toString();
                strIM = editIM.getText().toString();
                
                strSpinAddr = spinPostalAddr.getSelectedItem().toString();
                strStreet = editStreet.getText().toString();
                strPOBox = editPOBox.getText().toString();
                strCity = editCity.getText().toString();
                strState = editState.getText().toString();
                strZipCode = editZipCode.getText().toString();
                strCountry = editCountry.getText().toString();
                	
                strSpinSNS = spinSNS.getSelectedItem().toString();
                strSNS = editSNS.getText().toString();
                
                strSpinOrg = spinOrg.getSelectedItem().toString();
                strOrg = editOrg.getText().toString();
                
                strNotes = editNotes.getText().toString();
                strDate = Integer.toString(date.getDate());
                strHour = Integer.toString(date.getHours());
                strMin = Integer.toString(date.getMinutes());
                strSec = Integer.toString(date.getSeconds());
                
                if(btnMan.isChecked() == true)
            		contact_id = mDbHelper.createContact(strAndroidOwner, strTypes, strGivenName, strMiddleName, strFamilyName, strMan, strSpinPhone, strPhone, 
            				strSpinEmail, strEmail, strSpinIM, strIM, strSpinAddr, strStreet, strPOBox, strCity, strState, 
            				strZipCode, strCountry, strSpinSNS, strSNS, strSpinOrg, strOrg, strNotes, strDate, strHour, strMin, strSec);
            		
            	if(btnWoman.isChecked() == true)
            		contact_id = mDbHelper.createContact(strAndroidOwner, strTypes, strGivenName, strMiddleName, strFamilyName, strWoman, strSpinPhone, strPhone, 
            				strSpinEmail, strEmail, strSpinIM, strIM, strSpinAddr, strStreet, strPOBox, strCity, strState, 
            				strZipCode, strCountry, strSpinSNS, strSNS, strSpinOrg, strOrg, strNotes, strDate, strHour, strMin, strSec);
            }
        });
        
        	Context context = getApplicationContext();
        	int duration = Toast.LENGTH_SHORT;
      
        
//        	mHandler.postDelayed(new Runnable() {             
//            	public void run() {                 
//            		doStuff();             
//            		}         
//            }, 10000); 

        	
        	Thread t1 = new Thread(new Server(update, mDbHelper));
        	t1.start();
           
    } //onCreate
    
//    private void doStuff() {           
//    	Context context = getApplicationContext();
//      int duration = Toast.LENGTH_SHORT;
//    	
//    	Thread t = new Thread(new Client(mDbHelper));
//    	Toast toast1 = Toast.makeText(context,"Start client thread after 10 sec", duration);
//        toast1.show();        	
//        t.start(); 
//    	
//    } 
    

} //UDPWoo

class Client implements Runnable{
	 
	public static final String CLIENTIP="10.0.2.2";
    public static final int CLIENTPORT=5000;
    
    public static final String SERVERIP="10.0.2.15";
    public static final int SERVERPORT=5000;
    
    String strGivenName, strMiddleName, strFamilyName, strGender, strSpinPhone, 
	   	   strPhone, strSpinEmail, strEmail, strSpinIM, strIM, strSpinAddr, strStreet, strPOBox, strCity, strState, strZipCode,
	       strCountry, strSpinSNS, strSNS, strSpinOrg, strOrg, strNotes, strDate, strHour, strMin, strSec; 
	
    ContactDBAdapter mDbHelper_server;
     
    UpdatePacket update = new UpdatePacket();
    Thread t;
    
    public Client(ContactDBAdapter mDbHelper) {
            Log.i("client","inside constructor");            
            mDbHelper_server = mDbHelper;
    }
    
    //@Override
    public void run() {
                	
    	// TODO Auto-generated method stub
    	try{
    		InetAddress iadd = InetAddress.getByName(CLIENTIP);
            DatagramSocket socket = new DatagramSocket();
        	Cursor cursor = mDbHelper_server.fetchAllContacts();
        	
        	if( cursor != null){
            	if (cursor.moveToFirst()) {

            	do { 
            		if((cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_OWNER)).equalsIgnoreCase("Y"))
            			&& (cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_TYPES)).equalsIgnoreCase("update"))){

            			update.Owner = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_OWNER));
            			update.Types = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_TYPES));
            			update.GivenName = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_GIVENNAME));
            			update.MiddleName = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_MIDDLENAME));
            			update.FamilyName = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_FAMILYNAME));
            			update.Gender = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_GENDER));
            			update.SpinPhone = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_SPINPHONE));
            			update.Phone = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_PHONE));
            			update.SpinEmail = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_SPINEMAIL));
            			update.Email = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_EMAIL));
            			update.SpinIM = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_SPINIM));
            			update.IM = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_IM));
            			update.SpinPostalAddr = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_SPINADDR));
            			update.Street = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_STREET));
            			update.POBox = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_POBOX));
            			update.City = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_CITY));
            			update.State = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_STATE));
            			update.ZipCode = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_ZIPCODE));
            			update.Country = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_COUNTRY));
            			update.SpinSNS = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_SPINSNS));
            			update.SNS = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_SNS));
            			update.SpinOrg = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_SPINORG));
            			update.Org = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_ORG));
            			update.Notes = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_NOTES));
            			update.Date = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_DATE));
            			update.Hour = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_HOUR));
            			update.Min = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_MIN));
            			update.Sec = cursor.getString(cursor.getColumnIndex(ContactDBAdapter.KEY_SEC));
            			
            			update.setOwner(update.Owner);
            			update.setTypes(update.Types);
            			update.setGivenName(update.GivenName);   
            			update.setMiddleName(update.MiddleName);   
            			update.setFamilyName(update.FamilyName);           
            			update.setGender(update.Gender);                    
            			update.setSpinPhone(update.SpinPhone);                    
            			update.setPhone(update.Phone);                    
            			update.setSpinEmail(update.SpinEmail);                    
            			update.setEmail(update.Email);                     
            			update.setSpinIM(update.SpinIM);                    
            			update.setIM(update.IM);                     
            			update.setSpinPostalAddr(update.SpinPostalAddr);                    
            			update.setStreet(update.Street);                    
            			update.setPOBox(update.POBox);                    
            			update.setCity(update.City);                   
            			update.setState(update.State);                    
            			update.setZipCode(update.ZipCode);                    
            			update.setCountry(update.Country);                    
            			update.setSpinSNS(update.SpinSNS);                    
            			update.setSNS(update.SNS);                    
            			update.setSpinOrg(update.SpinOrg);                   
            			update.setOrg(update.Org);                    
            			update.setNotes(update.Notes);
            			update.setDate(update.Date);
            			update.setHour(update.Hour);
            			update.setMin(update.Min);
            			update.setSec(update.Sec);
                                    			
            			JSONObject jsonUpdate = new JSONObject();  
                  
            			jsonUpdate.put("Owner", update.getOwner()); 
            			jsonUpdate.put("Types", update.getTypes()); 
            			jsonUpdate.put("GivenName", update.getGivenName()); 
            			jsonUpdate.put("MiddleName", update.getMiddleName());  
            			jsonUpdate.put("FamilyName", update.getFamilyName()); 
            			jsonUpdate.put("Gender", update.getGender()); 
            			jsonUpdate.put("SpinPhone", update.getSpinPhone()); 
            			jsonUpdate.put("Phone", update.getPhone()); 
            			jsonUpdate.put("SpinEmail", update.getSpinEmail()); 
            			jsonUpdate.put("Email", update.getEmail()); 
            			jsonUpdate.put("SpinIM", update.getSpinIM()); 
            			jsonUpdate.put("IM", update.getIM()); 
            			jsonUpdate.put("SpinPostalAddr", update.getSpinPostalAddr()); 
            			jsonUpdate.put("Street", update.getStreet()); 
            			jsonUpdate.put("POBox", update.getPOBox()); 
            			jsonUpdate.put("City", update.getCity()); 
            			jsonUpdate.put("State", update.getState()); 
            			jsonUpdate.put("ZipCode", update.getZipCode()); 
            			jsonUpdate.put("Country", update.getCountry()); 
            			jsonUpdate.put("SpinSNS", update.getSpinSNS()); 
            			jsonUpdate.put("SNS", update.getSNS()); 
            			jsonUpdate.put("SpinOrg", update.getSpinOrg()); 
            			jsonUpdate.put("Org", update.getOrg()); 
            			jsonUpdate.put("Notes", update.getNotes()); 
            			jsonUpdate.put("Date", update.getDate()); 
            			jsonUpdate.put("Hour", update.getHour()); 
            			jsonUpdate.put("Min", update.getMin()); 
            			jsonUpdate.put("Sec", update.getSec()); 
                                  			
            			// json string representation of person object  
            			String stringUpdate = jsonUpdate.toString();  
            			DatagramPacket packet=new DatagramPacket( stringUpdate.getBytes(), stringUpdate.getBytes().length, iadd, CLIENTPORT);
            			Log.i("client","1");

            			socket.send(packet); 
            			Log.i("client","2");
            		}                                  
            	} while(cursor.moveToNext());                        	
            	
            	}//if                 	
        	}	
        	cursor.close(); 
        	socket.close();

    	} catch (UnknownHostException e) {
    		 // TODO Auto-generated catch block
             e.printStackTrace();
         }catch (SocketException e) {
        	 // TODO Auto-generated catch block
        	 e.printStackTrace();
         } catch (IOException e) {
        	 // TODO Auto-generated catch block
        	 e.printStackTrace();
         } catch (Exception e) {
        	 e.printStackTrace();
         }         
    } //run
} //Client 

class Server implements Runnable{
        
		public static final String SERVERIP = "10.0.2.15";
        public static final int SERVERPORT = 5000;
      
        public static final String CLIENTIP="10.0.2.2";
        public static final int CLIENTPORT=5000;
          
        boolean update_contact;
        UpdatePacket update = new UpdatePacket();
    	
        String strOwner, strTypes, strGivenName, strMiddleName, strFamilyName, strGender, strSpinPhone, 
		       strPhone, strSpinEmail, strEmail, strSpinIM, strIM, strSpinAddr, strStreet, strPOBox, strCity, strState, strZipCode,
	           strCountry, strSpinSNS, strSNS, strSpinOrg, strOrg, strNotes, strDate, strHour, strMin, strSec; 
        
        String Owner, Types, GivenName, FamilyName, Date, Hour, Min, Sec;
        
        int intDate, intHour, intMin, intSec;
        
    	ContactDBAdapter mDbHelper_server;
    	UpdatePacket total_contact;

        public Server(UpdatePacket total, ContactDBAdapter mDbHelper) {
                Log.i("server","inside constructor");
                total_contact = total;
                mDbHelper_server = mDbHelper;
        }
     
        @Override
        public void run() {
        	// TODO Auto-generated method stub        
            	try {
            		InetAddress inetadd= InetAddress.getByName(SERVERIP);
            		DatagramSocket socket= new DatagramSocket(SERVERPORT,inetadd);
            		
            		while(true){                	
            			try{
            				        				    				
            				byte[] buf = new byte[512];
            				DatagramPacket receive_packet= new DatagramPacket(buf,buf.length);
            				Log.i("server","1");
                           
            				socket.receive(receive_packet);
            				
            				Calendar cal = Calendar.getInstance();
                            long sec = cal.getTimeInMillis();
                            Date date = new Date();
                            int min = date.getMinutes();
                            
                            Log.i("udpsung receiving min", Integer.toString(min));
                            Log.i("udpsung receiving sec", Long.toString(sec));
            				Log.i("server","2");
            				
            				byte[] receive_data = receive_packet.getData();
            				String value = new String(receive_data);
            				Log.i("server","3");
            				 
            				strOwner = new JSONObject(value).getString("Owner");
            				strTypes = new JSONObject(value).getString("Types");
            				strGivenName = new JSONObject(value).getString("GivenName");  
            				strMiddleName = new JSONObject(value).getString("MiddleName");  
            				strFamilyName = new JSONObject(value).getString("FamilyName"); 	
            				strGender = new JSONObject(value).getString("Gender"); 	
            				strSpinPhone = new JSONObject(value).getString("SpinPhone"); 	
            				strPhone = new JSONObject(value).getString("Phone"); 	
            				strSpinEmail = new JSONObject(value).getString("SpinEmail"); 	
            				strEmail = new JSONObject(value).getString("Email"); 
            				strSpinIM = new JSONObject(value).getString("SpinIM"); 
            				strIM = new JSONObject(value).getString("IM"); 
            				strSpinAddr = new JSONObject(value).getString("SpinPostalAddr"); 
            				strStreet = new JSONObject(value).getString("Street"); 
            				strPOBox = new JSONObject(value).getString("POBox"); 
            				strCity = new JSONObject(value).getString("City"); 
            				strState = new JSONObject(value).getString("State"); 
            				strZipCode = new JSONObject(value).getString("ZipCode"); 
            				strCountry = new JSONObject(value).getString("Country");
            				strSpinSNS = new JSONObject(value).getString("SpinSNS"); 
            				strSNS = new JSONObject(value).getString("SNS"); 
            				strSpinOrg = new JSONObject(value).getString("SpinOrg"); 
            				strOrg = new JSONObject(value).getString("Org"); 
            				strNotes = new JSONObject(value).getString("Notes"); 
            				strDate =  new JSONObject(value).getString("Date"); 
            				strHour = new JSONObject(value).getString("Hour"); 
            				strMin = new JSONObject(value).getString("Min"); 
            				strSec = new JSONObject(value).getString("Sec");
            				
            				intDate =  Integer.parseInt(strDate);
            				intHour = Integer.parseInt(strHour); 
            				intMin = Integer.parseInt(strMin);
            				intSec = Integer.parseInt(strSec);
            				
            				Log.i("owner", strOwner);
            				Log.i("types", strTypes);
            				Log.i("givenname", strGivenName);
            				Log.i("gender", strGender);
            				Log.i("familyname", strFamilyName);
            				
            				InetAddress iadd = InetAddress.getByName(CLIENTIP);
            				
            				
            		        try {
            		        	Cursor cur = mDbHelper_server.fetchAllContacts(); 
            		        	
            		        	if(cur != null){
            		        		if (cur.moveToFirst()){
            		        			do{
            		        				
            		        				Owner = cur.getString(cur.getColumnIndex(ContactDBAdapter.KEY_OWNER));
            		        				Types = cur.getString(cur.getColumnIndex(ContactDBAdapter.KEY_TYPES));	
            		        				GivenName = cur.getString(cur.getColumnIndex(ContactDBAdapter.KEY_GIVENNAME));
            		        				FamilyName = cur.getString(cur.getColumnIndex(ContactDBAdapter.KEY_FAMILYNAME));
            		        				Date = cur.getString(cur.getColumnIndex(ContactDBAdapter.KEY_DATE));
            		        				Hour = cur.getString(cur.getColumnIndex(ContactDBAdapter.KEY_HOUR));
            		        				Min = cur.getString(cur.getColumnIndex(ContactDBAdapter.KEY_MIN));
            		        				Sec = cur.getString(cur.getColumnIndex(ContactDBAdapter.KEY_SEC)); 	
            		        				
            		        				Log.i("owner at UDPWoo DB", Owner);
            		        				Log.i("types at UDPWoo DB", Types);
            		        				Log.i("givenname at UDPWoo DB", GivenName);            	            				
            	            				Log.i("familyname at UDPWoo DB", FamilyName);
            	            				Log.i("date at UDPWoo DB", Date);
            	            				Log.i("hour at UDPWoo DB", Hour);
            	            				Log.i("min at UDPWoo DB", Min);
            	            				Log.i("sec at UDPWoo DB", Sec);
	
            	            				  if((strOwner.compareTo(Owner) == 0) ){ //if owner is the same
            	            						
            	            					if((strGivenName.compareTo(GivenName) ==  0) && (strFamilyName.compareTo(FamilyName) == 0) 
            	            						&& (strDate.compareTo(Date) == 0) && (strHour.compareTo(Hour) == 0) && (strMin.compareTo(Min) == 0) && (strSec.compareTo(Sec) == 0))	        			            		        				
            	            							Log.i("Stop update", "Stop update");        		        				  		
            	            					else if((strGivenName.compareTo(GivenName) ==  0) && (strFamilyName.compareTo(FamilyName) == 0) 
            	            						&& ((strDate.compareTo(Date) != 0) || (strHour.compareTo(Hour) != 0) || (strMin.compareTo(Min) != 0) || (strSec.compareTo(Sec) != 0))){            		        					
            		        						Thread t = new Thread(new Client(mDbHelper_server));        		        				                		        				   
            		        						t.start();   					
            		        					}
            	            					
            	            				  } else { //if owner is different
            	            					  if((strGivenName.compareTo(GivenName) ==  0) && (strFamilyName.compareTo(FamilyName) == 0)){ 		        			          
              		        						String rowId = cur.getString(cur.getColumnIndexOrThrow(ContactDBAdapter.KEY_ROWID));                                                
              		        						update_contact = mDbHelper_server.updateContact(Long.parseLong(rowId), "N", strTypes, strGivenName, strMiddleName, strFamilyName, strGender, strSpinPhone, 
              		        						 		 		strPhone, strSpinEmail, strEmail, strSpinIM, strIM, strSpinAddr, strStreet, strPOBox, strCity, strState, strZipCode,
              		        						 		 		strCountry, strSpinSNS, strSNS, strSpinOrg, strOrg, strNotes, strDate, strHour, strMin, strSec);               		                    		        						     						
              		        						DatagramPacket packet=new DatagramPacket( value.getBytes(), value.getBytes().length, iadd, CLIENTPORT);
              	                      			  	Log.i("updateclient","1");
              	                      			  	socket.send(packet); 
              	                      			  	Log.i("updateclient","2");
            	            					 } //if
            	            					              	            					             	            				 
            	            				  } //else            	            					            	            				       		        			
            		        			}while (cur.moveToNext());
            		        		} //if
            		        	} // if
            		        	cur.close();              		        	
            		        } catch (Exception e) {
            					e.printStackTrace();
            		        }            		     
            			} catch(IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch(NullPointerException e) {
                        	e.printStackTrace();	
                        } catch (JSONException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					} 
            		} //while  
            		
            } catch (UnknownHostException e) {
            	// TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SocketException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();       
            }             
     } //run
            	   
} //Server

