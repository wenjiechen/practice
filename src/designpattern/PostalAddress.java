package designpattern;

public class PostalAddress {

  private String address;
  private String Country;

  public static class Builder {
    private String address;
    private String Country;

    public Builder address(String address) {
      this.address = address;
      return this;
    }

    public Builder country(String country) {
      this.Country = country;
      return this;
    }

    public PostalAddress build() {
      return new PostalAddress(this);
    }
  }

  private PostalAddress(Builder builder) {
    address = builder.address;
    Country = builder.Country;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
