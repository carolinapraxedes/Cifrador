package teste;

import java.util.Scanner;

public class CircularCipher {
    private String allowedCharacters;

    public CircularCipher(String allowedCharacters) {
        this.allowedCharacters = allowedCharacters;
    }

    public String encrypt(String message, int[] shifts) {
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            int charIndex = allowedCharacters.indexOf(c);
            if (charIndex != -1) {
                int shift = shifts[i % shifts.length]; // Cicla pelos deslocamentos especificados
                int newIndex = (charIndex + shift) % allowedCharacters.length();
                encryptedMessage.append(allowedCharacters.charAt(newIndex));
            } else {
                // Caracteres não encontrados na lista permitida são mantidos inalterados.
                encryptedMessage.append(c);
            }
        }

        return encryptedMessage.toString();
    }
    
    public String decrypt(String encryptedMessage, int[] shifts) {
        StringBuilder decryptedMessage = new StringBuilder();

        for (int i = 0; i < encryptedMessage.length(); i++) {
            char c = encryptedMessage.charAt(i);
            int charIndex = allowedCharacters.indexOf(c);
            if (charIndex != -1) {
                int shift = shifts[i % shifts.length]; // Cicla pelos deslocamentos especificados
                int newIndex = (charIndex - shift + allowedCharacters.length()) % allowedCharacters.length();
                decryptedMessage.append(allowedCharacters.charAt(newIndex));
            } else {
                // Caracteres não encontrados na lista permitida são mantidos inalterados.
                decryptedMessage.append(c);
            }
        }

        return decryptedMessage.toString();
    }

    public static void main(String[] args) {
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ,.;0123456789-";
        CircularCipher cipher = new CircularCipher(allowedChars);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a mensagem: ");
        String message = scanner.nextLine();
        int[] shifts = new int[3];

        for (int i = 0; i < 3; i++) {
            System.out.print("Digite o " + (i + 1) + "º shift: ");
            shifts[i] = scanner.nextInt();
        }

        String encrypted = cipher.encrypt(message, shifts);
        System.out.println("Mensagem criptografada: " + encrypted);
        
        String decrypted = cipher.decrypt(encrypted, shifts);
        System.out.println("Mensagem descriptografada: " + decrypted);
    }
}



