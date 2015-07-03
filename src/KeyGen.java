import it.unisa.dia.gas.jpbc.Element;

//This class generates the required keys for a user.
public class KeyGen {
	private User user;
	private Params params;
	
	public KeyGen(Params params){
		this.params = params;
	}
	
	public User generate(){
		user.setUserSK(params.getPairing().getZr().newRandomElement().getImmutable()); //private key
		user.setUserPK(params.getgpre().powZn(user.getUserSK()).getImmutable());
		user.setUserISK(user.getUserSK().invert().getImmutable()); //invert the secret key to calculate the proxy re-encryption key
		return user;
	}
	
	public Element generateRK(User owner, User user1){
		return user.getUserPK().powZn(owner.getUserISK()).getImmutable();
	}
}
