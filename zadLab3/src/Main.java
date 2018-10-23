public class Main {
    public static void main(String[] args) {
        try {
            EmailMessage wiadomosc = EmailMessage.builder()
                    .addFrom("skomtest@op.pl")
                    .addTo("skomtest@op.pl")
                    .addSubject("Mail testowy")
                    .addContent("Brak tresci")
                    .build();

            wiadomosc.send("Skomtest1");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
