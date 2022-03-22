package api.models.http;

public class ParentPostDTO extends UserPostDTO {

    private float fiscalNumber;

    public ParentPostDTO(String firstname, String lastname, String password, String email, String address,
            String phoneNumber) {
        super(firstname, lastname, password, email, address, phoneNumber);
    }

    public float getFiscalNumber() {
        return fiscalNumber;
    }

    public void setFiscalNumber(float fiscalNumber) {
        this.fiscalNumber = fiscalNumber;
    }
}
