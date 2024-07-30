package designs;

 class Computer {
      int RAM,storage;
      String CPU;
       Computer(Builder builder) {
          this.CPU = builder.CPU;
          this.RAM = builder.RAM;
          this.storage = builder.storage;
          
      }
       public String getCPU() {
           return CPU;
       }

       public int getRAM() {
           return RAM;
       }

       public int getStorage() {
           return storage;
       }
       public void displaySpecs() {
           System.out.println("Computer Specifications:");
           System.out.println("CPU: " + CPU);
           System.out.println("RAM: " + RAM + " GB");
           System.out.println("Storage: " + storage + " GB " );
       }

    public static  class Builder{
    	        private String CPU;
    	        private int RAM;
    	        private int storage;
    	        public Builder setCPU(String CPU) {
    	            this.CPU = CPU;
    	            return this;
    	        }

    	        public Builder setRAM(int RAM) {
    	            this.RAM = RAM;
    	            return this;
    	        }

    	        public Builder setStorage(int storage) {
    	            this.storage = storage;
    	            return this;
    	        }

    	        public Computer build() {
    	            return new Computer(this);
    	        }
    	    

      }
}
class BuilderPatternComputer
{

		public static void main(String[] args) {
	       
	        Computer basicComputer = new Computer.Builder()
	            .setCPU("Ryzen")
	            .setRAM(4)
	            .setStorage(520)
	            .build();
	        System.out.println(" Computer Configuration:");
	        basicComputer.displaySpecs();
	        System.out.println();
	}
}
