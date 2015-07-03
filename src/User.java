import it.unisa.dia.gas.jpbc.Element;


//This class allows easy access to user keys
public class User {
	private Element pk, sk, isk, rk;
	
	public void setUserPK(Element pk){
		this.pk = pk;
	}
	
	public void setUserSK(Element sk){
		this.sk = sk;
	}
	
	public void setUserISK(Element isk){
		this.isk = isk;
	}
	
	public void setUserReEncryptKey(Element rk){
		this.rk = rk;
	}
	
	public Element getUserPK(){
		return this.pk;
	}
	
	public Element getUserSK(){
		return this.sk;
	}
	
	public Element getUserISK(){
		return this.isk;
	}
	
	public Element getUserReEncryptKey(){
		return this.rk;
	}
}
