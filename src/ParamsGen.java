import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPowPreProcessing;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.jpbc.PairingParametersGenerator;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;


public class ParamsGen {
	public Pairing pairing;
	public static Field zr, g1, gt;
	public static Element pk_a, sk_a, isk_a, isk_b, pk_b, sk_b, ownersk_a, g, k, g_k, z_k, e, rka_b;
	public void generate(){
		//Get the curve parameters
				PairingParametersGenerator pg = new TypeACurveGenerator(80, 256);
				PairingParameters curveParams = pg.generate();
				//PairingParameters curveParams = PairingFactory.getPairingParameters("a_181_603.properties");
				this.pairing = PairingFactory.getPairing(curveParams);
				
				//Initialize the parameters for second-level encryption
				g1 = pairing.getG1();
			    gt = pairing.getGT();
			    zr = pairing.getZr();
			    g = g1.newRandomElement().getImmutable();
				ElementPowPreProcessing gPre = g.getElementPowPreProcessing();
			    k= zr.newRandomElement().getImmutable();
			    g_k = gPre.powZn(k).getImmutable();
				z_k = pairing.pairing(g, g_k).getImmutable();
							      
				//Generate data owner keys
				sk_a = pairing.getZr().newRandomElement().getImmutable(); //private key
				pk_a = gPre.powZn(sk_a).getImmutable();
				isk_a = sk_a.invert().getImmutable(); //invert the secret key to calculate the proxy re-encryption key
				
				
				//Generate user keys (USER1)
				sk_b = pairing.getZr().newRandomElement().getImmutable(); //private key
				pk_b = gPre.powZn(sk_b).getImmutable();
				isk_b = sk_b.invert().getImmutable();
				
				//Generate proxy re-encryption keys
				rka_b = pk_b.powZn(isk_a).getImmutable();
	}
	
}
