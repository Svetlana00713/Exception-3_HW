import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в формате: Фамилия Имя Отчество датарождения(dd.mm.yyyy) номертелефона(без символов) пол");
        String userInput = scanner.nextLine();

        try {
            writeUserDataToFile(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    private static void writeUserDataToFile(String userInput) throws IOException {
        String[] userData = userInput.split(" ");

        if (userData.length != 6) {
            throw new IllegalArgumentException("Неверное количество данных. Ожидается 6 элементов.");
        }

        String name = userData[0];
        String patronymic = userData[1];
        String surname = userData[2];
        String birthDate = userData[3];
        String phoneNumber = userData[4];
        String gender = userData[5];

        validateData(name, patronymic, surname, birthDate, phoneNumber, gender);

        String fileName = name + ".txt";
        String userDataString = name + " " + patronymic + " " + surname + ", " + birthDate + ", " + phoneNumber + ", " + gender;

        FileWriter fileWriter = new FileWriter(fileName, true);
        fileWriter.write(userDataString + System.lineSeparator());
        fileWriter.close();

        System.out.println("Данные успешно записаны в файл " + fileName);
    }

    private static void validateData(String name, String patronymic, String surname, String birthDate, String phoneNumber, String gender) {
        if (!birthDate.matches("\\d{2}.\\d{2}.\\d{4}")) {
            throw new IllegalArgumentException("Неверный формат даты рождения. Ожидается dd.mm.yyyy");
        }

        try {
            Long.parseLong(phoneNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат номера телефона. Ожидается целое беззнаковое число.");
        }

        if (!gender.equals("f") && !gender.equals("m")) {
            throw new IllegalArgumentException("Неверный формат пола. Ожидается символ 'f' или 'm'.");
        }
    }
}
