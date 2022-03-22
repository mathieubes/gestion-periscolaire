package api.models.http;

public class ParentPostDTO extends UserPostDTO {

    private float fiscalNumber;

    public ParentPostDTO(String firstname, String lastname, String password, String email, String address, String phoneNumber, float fiscalNumber) {
        super(firstname, lastname, password, email, address, phoneNumber);
        this.fiscalNumber = fiscalNumber;
    }

    public float getFiscalNumber() {
        return fiscalNumber;
    }

    public void setFiscalNumber(float fiscalNumber) {
        this.fiscalNumber = fiscalNumber;
    }
}
