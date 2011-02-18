package npu.dce.woo;

public class UpdatePacket {
	
//	public int MAX_DATA_LENGTH = 512;
//
//    /*** The length of the data. ***/
//    int _length;
//
//    /*** Byte array of the data. ***/
//    byte[] _buf = null;
//
//    int Version;
    String Types;
	String GivenName;
	String MiddleName;
	String FamilyName;
	String Gender;
	String SpinPhone;
	String Phone;
	String SpinEmail;
	String Email;
	String SpinIM;
	String IM;
	String SpinPostalAddr;
	String Street;
	String POBox;
	String City;
	String State;
	String ZipCode;
	String Country;
	String SpinSNS;
	String SNS;
	String SpinOrg;
	String Org;
	String Notes;
	String Time;
	
//	int GivenNameLength;
//	int MiddleNameLength;
//	int FamilyNameLength;
//	int GenderLength;
//	int SpinPhoneLength;
//	int PhoneLength;
//	int SpinEmailLength;
//	int EmailLength;
//	int SpinIMLength;
//	int IMLength;
//	int SpinPostalAddrLength;
//	int StreetLength;
//	int POBoxLength;
//	int CityLength;
//	int StateLength;
//	int ZipCodeLength;
//	int CountryLength;
//	int SpinSNSLength;
//	int SNSLength;
//	int SpinOrgLength;
//	int OrgLength;
//	int NotesLength;
//	
//	long ToUnSigned;
	
	public UpdatePacket(){}
	
//    public UpdatePacket(byte[] buf, int length){
//    	_buf = buf;
//    	_length = length;
//
//    	if (length > MAX_DATA_LENGTH)
//    		_length = MAX_DATA_LENGTH;
//        else
//        	_length = length;
//    } 
    
    // get field length
//    int getGivenNameLength(){
//    	return GivenNameLength;
//    }
//    
//    int getMiddleNameLength(){
//    	return MiddleNameLength;
//    }
//    
//    int getFamilyNameLength(){
//    	return FamilyNameLength;
//    }
//  
//    int getGenderLength(){
//    	return GenderLength;
//    }
//    
//    int getSpinPhoneLength(){
//    	return SpinPhoneLength;
//    }
//    
//    int getPhoneLength(){
//    	return PhoneLength;
//    }
//    
//    int getSpinEmailLength(){
//    	return SpinEmailLength;
//    }
//    
//    int getEmailLength(){
//    	return EmailLength;
//    }
//    
//    int getSpinIMLength(){
//    	return SpinIMLength;
//    }
//    
//    int getIMLength(){
//    	return IMLength;
//    }
//    
//    int getSpinPostalAddrLength(){
//    	return SpinPostalAddrLength;
//    }
//    
//    int getStreetLength(){
//    	return StreetLength;
//    }
//    
//    int getPOBoxLength(){
//    	return POBoxLength;
//    }
//    
//    int getCityLength(){
//    	return CityLength;
//    }
//    
//    int getStateLength(){
//    	return StateLength;
//    }
//    
//    int getZipCodeLength(){
//    	return ZipCodeLength;
//    }
//    
//    int getCountryLength(){
//    	return CountryLength;
//    }
//    
//    int getSpinSNSLength(){
//    	return SpinSNSLength;
//    }
//    
//    int getSNSLength(){
//    	return SNSLength;
//    }
//    
//    int getSpinOrgLength(){
//    	return SpinOrgLength;
//    }
//    
//    int getOrgLength(){
//    	return OrgLength;
//    }
//    
//    int getNotesLength(){
//    	return NotesLength;
//    }
//    
    //set field length
//    
//    void setGivenNameLength(int givenname_length){
//    	GivenNameLength = givenname_length;
//    }
//    
//    void setMiddleNameLength(int middlename_length){
//    	MiddleNameLength = middlename_length;
//    }
//    
//    void setFamilyNameLength(int familyname_length){
//    	FamilyNameLength = familyname_length;
//    }
//  
//    void setGenderLength(int gender_length){
//    	GenderLength = gender_length;
//    }
//    
//    void setSpinPhoneLength(int spinphone_length){
//    	SpinPhoneLength = spinphone_length;
//    }
//    
//    void setPhoneLength(int phone_length){
//    	PhoneLength = phone_length;
//    }
//    
//    void setSpinEmailLength(int spinemail_length){
//    	SpinEmailLength = spinemail_length;
//    }
//    
//    void setEmailLength(int email_length){
//    	EmailLength = email_length;
//    }
//    
//    void setSpinIMLength(int spinim_length){
//    	SpinIMLength = spinim_length;
//    }
//    
//    void setIMLength(int im_length){
//    	IMLength = im_length;
//    }
//    
//    void setSpinPostalAddrLength(int spinpostaladdr_length){
//    	SpinPostalAddrLength = spinpostaladdr_length;
//    }
//    
//    void setStreetLength(int street_length){
//    	StreetLength = street_length;
//    }
//    
//    void setPOBoxLength(int pobox_length){
//    	POBoxLength = pobox_length;
//    }
//    
//    void setCityLength(int city_length){
//    	CityLength = city_length;
//    }
//    
//    void setStateLength(int state_length){
//    	StateLength = state_length;
//    }
//    
//    void setZipCodeLength(int zipcode_length){
//    	ZipCodeLength = zipcode_length;
//    }
//    
//    void setCountryLength(int country_length){
//    	CountryLength = country_length;
//    }
//    
//    void setSpinSNSLength(int spinsns_length){
//    	SpinSNSLength = spinsns_length;
//    }
//    
//    void setSNSLength(int sns_length){
//    	SNSLength = sns_length;
//    }
//    
//    void setSpinOrgLength(int spinorg_length){
//    	SpinOrgLength = spinorg_length;
//    }
//    
//    void setOrgLength(int org_length){
//    	OrgLength = org_length;
//    }
//    
//    void setNotesLength(int notes_length){
//    	NotesLength = notes_length;
//    }
//    
    // get field value
//    int getVersion(){
//    	return Version;
//    }
    
    String getTypes(){
        return Types;
    }
    
    String getGivenName(){ 
    	 return GivenName;
    }
    
	String getMiddleName(){ 
		return MiddleName;
	}
	
	String getFamilyName(){
		return FamilyName;
	}
	
	String getGender(){
		return Gender;
	}
	
	String getSpinPhone(){
		return SpinPhone;
	}
	
	String getPhone(){ 
		return Phone;
	}
	
	String getSpinEmail(){ 
		return SpinEmail;
	}
	
	String getEmail(){ 
		return Email;
	}
	
	String getSpinIM(){
		return SpinIM;
	}
	
	String getIM(){ 
		return IM;
	}
	
	String getSpinPostalAddr(){
		return SpinPostalAddr;
	}
	
	String getStreet(){ 
		return Street;
	}
	
	String getPOBox(){ 
		return POBox;
	}
	
	String getCity(){ 
		return City;
	}
	
	String getState(){
		return State;
	}
	
	String getZipCode(){
		return ZipCode;
	}
	
	String getCountry(){ 
		return Country;
	}
	
	String getSpinSNS(){
		return SpinSNS;
	}
	
	String getSNS(){ 
		return SNS;
	}
	
	String getSpinOrg(){ 
		return SpinOrg;
	}
	
	String getOrg(){ 
		return Org;
	}
	
	String getNotes(){ 
		return Notes;
	}
	
	String getTime(){ 
		return Time;
	}
	
//	void setVersion(byte[] buf, int startpos, int length){
//		
//		long ToUnSigned = 0;
//		  
//		for(int i=0; i < length; i++){
//			ToUnSigned += ((long) toUnsigned((int)buf[i]) << (8 *(length - (i+1))  ));  	  
//		}
//    	
//		for(int j=startpos; j < startpos+length; j++)
//			  _buf[j] = (byte)((ToUnSigned >> 8*((startpos+length) - (j+1))) & 0xff);
//    }

    void setTypes(String types){
    	Types = types;
    }

    void setGivenName(String str_givenname){
    	
    	GivenName = str_givenname;
//    	long ToUnSigned = 0;
//		  
//		for(int i=0; i < length; i++){
//			ToUnSigned += ((long) toUnsigned((int)buf[i]) << (8 *(length - (i+1))  ));  	  
//		}
//    	
//		for(int j=startpos; j < startpos+length; j++)
//			  _buf[j] = (byte)((ToUnSigned >> 8*((startpos+length) - (j+1))) & 0xff);
    }

    void setMiddleName(String str_middlename){
    	MiddleName = str_middlename;
    }

    void setFamilyName(String str_familyname){
    	FamilyName = str_familyname;	
    }

    void setGender(String str_gender){    	
    	Gender = str_gender;
    }

    void setSpinPhone(String str_spinphone){ 	
    	SpinPhone = str_spinphone;
    }

    void setPhone(String str_phone){ 	
    	Phone = str_phone;
    }

    void setSpinEmail(String str_spinemail){
    	SpinEmail = str_spinemail;
    }

    void setEmail(String str_email){
    	Email = str_email;
    }

    void setSpinIM(String str_spinim){
    	SpinIM = str_spinim;
    }

    void setIM(String str_im){ 
    	IM = str_im;
    }

    void setSpinPostalAddr(String str_spinpostaladdr){ 	
    	SpinPostalAddr = str_spinpostaladdr;
    }

    void setStreet(String str_street){ 	
    	Street = str_street;
    }

    void setPOBox(String str_pobox){
 	    POBox = str_pobox;
    }

    void setCity(String str_city){    	
    	City = str_city;
    }

    void setState(String str_state){
    	State = str_state;
    }

    void setZipCode(String str_zipcode){
    	ZipCode = str_zipcode;
    }

    void setCountry(String str_country){
    	Country  = str_country;
    }

    void setSpinSNS(String str_spinsns){
    	SpinSNS = str_spinsns;
    }

    void setSNS(String str_sns){
    	SNS = str_sns;
    }

    void setSpinOrg(String str_spinorg){
    	SpinOrg = str_spinorg;
    }

    void setOrg(String str_org){
    	Org = str_org;
    }

    void setNotes(String str_notes){
    	Notes = str_notes;
    }
     
    void setTime(String str_time){
    	Time = str_time;
    }
    
    
//    /**
//	 * Convert a signed byte to unsigned byte.
//	 *
//	 * @param val the signed byte to be converted.
//	 * @return the equivalent unsigned value.
//	 */
//
//    int toUnsigned(int val)
//	{
//		return val < 0 ? 256 + val : val;
//	}

}
