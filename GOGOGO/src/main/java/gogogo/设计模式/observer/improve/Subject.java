package gogogo.设计模式.observer.improve;

//�ӿ�, ��WeatherData ��ʵ�� 
public interface Subject {
	
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}
