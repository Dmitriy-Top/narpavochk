package utils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import entity.DODiplomLearner;
import entity.DOUdoLearner;
import entity.DiplomLearner;
import entity.UdoLearner;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by admin on 18.02.2017.
 */
public class LearnerDAO {
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();
    public static final String NO_DATA_FOUND = "No data found.";
    private static HttpTransport HTTP_TRANSPORT;
    private static FileDataStoreFactory DATA_STORE_FACTORY;
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/sheets.googleapis.com-java-quickstart");
    private static final String APPLICATION_NAME =
            "Google Sheets API";
    private static final List<String> SCOPES =
            Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final String UDO_SPREEDSHEET_ID = "1xDHJgVf97dhIrhoQYIs_yP4r4tka8x93km3S1UdIUqM";
    private static final String DIPLOM_SPREEDSHEET_ID = "1KKXZPq6N0blwPfS-HYsVWKb1VCr8Yz1uNv3uDrYA3GM";
    private static final String[] UDO_SPREEDSHEET_RANGE = {"2016!A4:I", "2015!A4:I", "СПР 2016!A4:i", "Свыше 100 '15!A4:I", "Свыше 100 '16!A4:I"};
    private static final String[] DIPLOM_SPREEDSHEET_RANGE = {"2016!A5:J", "2015!A5:J"};
    private static final String DO_UDO_SPREEDSHEET_RANGE = "2016 ДО !A4:I";
    private static final String DO_DIPLOM_SPREEDSHEET_RANGE = "2016 ДО!A5:G";


    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static Sheets getSheetsService() throws IOException {
        Credential credential = authorize();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
                LearnerDAO.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");
        return credential;
    }

    private static List<List<Object>> getDataFromSheet(String range, String sheetID) throws IOException {
        Sheets service = getSheetsService();
        ValueRange response = service.spreadsheets().values()
                .get(sheetID, range)
                .execute();
        List<List<Object>> values = response.getValues();
        return values;
    }

    private static List<List<Object>> getDataFromSheet(String range[], String sheetID) throws IOException {
        Sheets service = getSheetsService();
        List<List<Object>> values = new ArrayList<>();
        for (int i = 0; i < range.length; i++) {
            ValueRange response = service.spreadsheets().values()
                    .get(sheetID, range[i])
                    .execute();
            values.addAll(response.getValues());
        }
        return values;
    }

    public static UdoLearner getUdoLearner(String nummOfUdo, String regNummOfDoc) throws IOException {
        List<List<Object>> values = getDataFromSheet(UDO_SPREEDSHEET_RANGE, UDO_SPREEDSHEET_ID);
        UdoLearner result = null;
        for (List<Object> row : values) {
            if (nummOfUdo.trim().equals(row.get(0).toString().trim())) {
                if (regNummOfDoc.trim().equals(row.get(1).toString().trim())) {
                    String dateOfDoc = chkStrNotEmpty(row, 2);
                    String FIO = chkStrNotEmpty(row, 3);
                    String courseName = chkStrNotEmpty(row, 4);
                    String courseHourse = chkStrNotEmpty(row, 5);
                    String timeOfLearning = chkStrNotEmpty(row, 6);
                    String orderOfBeginLearn = chkStrNotEmpty(row, 7);
                    String orderOfLearnEnd = chkStrNotEmpty(row, 8);
                    result = new UdoLearner(regNummOfDoc, dateOfDoc, FIO, courseName, courseHourse, timeOfLearning, orderOfBeginLearn, orderOfLearnEnd, nummOfUdo);
                    break;
                }
            }
        }
        return result;
    }

    public static DOUdoLearner getDUUdoLearner(String regNummOfDoc) throws IOException {
        List<List<Object>> values = getDataFromSheet(DO_UDO_SPREEDSHEET_RANGE, UDO_SPREEDSHEET_ID);
        DOUdoLearner result = null;
        for (List<Object> row : values) {
            if (regNummOfDoc.trim().equals(row.get(0).toString().trim())) {
                String dateOfDoc = chkStrNotEmpty(row, 1);
                String FIO = chkStrNotEmpty(row, 2);
                String courseName = chkStrNotEmpty(row, 3);
                String courseHourse = chkStrNotEmpty(row, 4);
                String timeOfLearning = chkStrNotEmpty(row, 5);
                String orderOfBeginLearn = chkStrNotEmpty(row, 6);
                String orderOfLearnEnd = chkStrNotEmpty(row, 7);
                result = new DOUdoLearner(regNummOfDoc, dateOfDoc, FIO, courseName, courseHourse, timeOfLearning, orderOfBeginLearn, orderOfLearnEnd);
                break;
            }
        }
        return result;
    }

    public static DiplomLearner getDiplomLearner(String nummOfDiplom, String regNummOfDoc) throws IOException {
        List<List<Object>> values = getDataFromSheet(DIPLOM_SPREEDSHEET_RANGE, DIPLOM_SPREEDSHEET_ID);
        DiplomLearner result = null;
        for (List<Object> row : values) {
            if (nummOfDiplom.trim().equals(row.get(0).toString().trim())) {
                if (regNummOfDoc.trim().equals(row.get(2).toString().trim())) {
                    String nummOfDiplomAttach = chkStrNotEmpty(row, 1);
                    String dateOfDoc = chkStrNotEmpty(row, 3);
                    String FIO = chkStrNotEmpty(row, 4);
                    String courseName = chkStrNotEmpty(row, 5);
                    String courseHourse = chkStrNotEmpty(row, 6);
                    String nameOfQuality = chkStrNotEmpty(row, 7);
                    String assistDate = chkStrNotEmpty(row, 8);
                    String orderOfCourseEnd = chkStrNotEmpty(row, 9);
                    result = new DiplomLearner(regNummOfDoc, dateOfDoc, FIO, courseName, courseHourse, assistDate, orderOfCourseEnd, nummOfDiplom, nummOfDiplomAttach, nameOfQuality);
                    break;
                }
            }
        }
        return result;
    }

    public static DODiplomLearner getDODiplomLearner(String regNummOfDoc) throws IOException {
        List<List<Object>> values = getDataFromSheet(DO_DIPLOM_SPREEDSHEET_RANGE, DIPLOM_SPREEDSHEET_ID);
        DODiplomLearner result = null;
        for (List<Object> row : values) {
            if (regNummOfDoc.trim().equals(row.get(0).toString().trim())) {
                String dateOfDoc = chkStrNotEmpty(row, 1);
                String FIO = chkStrNotEmpty(row, 2);
                String courseName = chkStrNotEmpty(row, 3);
                String courseHourse = chkStrNotEmpty(row, 4);
                String assistDate = chkStrNotEmpty(row, 5);
                String orderOfCourseEnd = chkStrNotEmpty(row, 6);
                result = new DODiplomLearner(regNummOfDoc, dateOfDoc, FIO, courseName, courseHourse, assistDate, orderOfCourseEnd);
                break;
            }
        }
        return result;
    }

    private static String chkStrNotEmpty(List list, int i) {
        StringBuilder result = new StringBuilder();
        try {
            result.append(list.get(i));
        } catch (Exception e) {
            result.append("-");
        }
        return result.toString();
    }

}
