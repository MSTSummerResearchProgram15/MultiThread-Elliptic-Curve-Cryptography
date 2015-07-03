import it.unisa.dia.gas.jpbc.Element;

//This class generates the required keys for a user.
public class KeyGen {
	private User user;
	private Params params;
	
	public KeyGen(Params params){
		this.params = params;
	}
	
	public User generate(){
		user.setSK(params.getPairing().getZr().newRandomElement().getImmutable()); //private key
		user.setPK(params.getgpre().powZn(user.getSK()).getImmutable());
		user.setISK(user.getSK().invert().getImmutable()); //invert the secret key to calculate the proxy re-encryption key
		return user;
	}
	
	public Element generateRK(User owner, User user1){
		return user.getUserPK().powZn(owner.getISK()).getImmutable();
	}
}
