package designs;


 interface Image {
    void display();
}
 class RealImage implements Image {
    private String imageName;

    public RealImage(String imageName) {
        this.imageName = imageName;
        loadImageFromServer();
    }

    private void loadImageFromServer() {
        System.out.println("Loading image: " + imageName);
        
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + imageName);
    }
}
 class ProxyImage implements Image {
    private RealImage realImage;
    private String imageName;

    public ProxyImage(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(imageName);
        }
        realImage.display();
    }
}
class ProxyPattern
{
	public static void main(String[] args) {
        Image image1 = new ProxyImage("test_image_1.jpg");
        Image image2 = new ProxyImage("test_image_2.jpg");

        image1.display();
        image1.display();
        
        image2.display();
        image2.display();
    }
}
