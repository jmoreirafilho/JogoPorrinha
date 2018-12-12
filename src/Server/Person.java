package Server;

import java.net.InetAddress;

public class Person {

	public InetAddress address;
	public Integer toothpickQty;
	public Integer hunch;
	public String username;
	
	public InetAddress getAddress() {
		return address;
	}
	public void setAddress(InetAddress address) {
		this.address = address;
	}
	public Integer getToothpickQty() {
		return toothpickQty;
	}
	public void setToothpickQty(Integer toothpickQty) {
		this.toothpickQty = toothpickQty;
	}
	public Integer getHunch() {
		return hunch;
	}
	public void setHunch(Integer hunch) {
		this.hunch = hunch;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return this.username;
	}
	
}
