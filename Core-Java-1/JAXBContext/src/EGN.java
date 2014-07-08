import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EGN {
	@XmlElement
	private long egn;
	
	private EGN() {
	}
	
	@XmlElement
	public long getEgn() {
		return egn;
	}
	
	public EGN(long egn) {
		super();
		this.egn = egn;
	}
}
