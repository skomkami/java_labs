public class Main {
    public static void main(String[] args) {
        try {
            EmailMessage wiadomosc = EmailMessage.builder()
                    .addFrom("user@op.pl")
                    .addTo("user2@op.pl")
                    .addSubject("Mail testowy")
                    .addContent("Brak tresci")
                    .build();

            wiadomosc.send("password");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
