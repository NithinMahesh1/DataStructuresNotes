package log;

public class SuspectEntry extends LogParser{
	
	String name;
	String phoneNumber;
	String passportNumber;
	
	public SuspectEntry(String name, String phoneNumber, String passportNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.passportNumber = passportNumber;
	}
	
	public String getAll() {
		String save = name + "," + phoneNumber + "," + passportNumber;
		return save;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getPassportNumber() {
		return passportNumber;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((passportNumber == null) ? 0 : passportNumber.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SuspectEntry other = (SuspectEntry) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (passportNumber == null) {
			if (other.passportNumber != null)
				return false;
		} else if (!passportNumber.equals(other.passportNumber))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}

}
