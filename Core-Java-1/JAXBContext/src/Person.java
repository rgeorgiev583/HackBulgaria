import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
	private String name;
	private EGN egn;
	
	public List<Person> relatives = new ArrayList();
	
	
	
	public void addRelative()
}
