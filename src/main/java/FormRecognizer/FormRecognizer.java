package FormRecognizer;
import com.azure.ai.formrecognizer.documentanalysis.DocumentAnalysisClient;
import com.azure.ai.formrecognizer.documentanalysis.DocumentAnalysisClientBuilder;
import com.azure.ai.formrecognizer.documentanalysis.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.polling.SyncPoller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class FormRecognizer {
    private final static String ENDPOINT = "https://form-recognizer-techchallenge.cognitiveservices.azure.com/";
    private final static String API_KEY = "c13b6ddbc6044ff0a0d98a3463f9bc64";

    public static void main(String[] args) {
        try {
            DocumentAnalysisClient documentAnalysisClient = new DocumentAnalysisClientBuilder()
                    .credential(new AzureKeyCredential(API_KEY))
                    .endpoint(ENDPOINT)
                    .buildClient();

            String receiptUrl = "https://raw.githubusercontent.com/Azure/azure-sdk-for-java/main/sdk/formrecognizer"
                    + "/azure-ai-formrecognizer/src/samples/resources/sample-documents/receipts/contoso-allinone.jpg";

            SyncPoller<OperationResult, AnalyzeResult> analyzeReceiptPoller =
                    documentAnalysisClient.beginAnalyzeDocumentFromUrl("prebuilt-receipt", receiptUrl);

            AnalyzeResult receiptResults = analyzeReceiptPoller.getFinalResult();

            for (int i = 0; i < receiptResults.getDocuments().size(); i++) {
                AnalyzedDocument analyzedReceipt = receiptResults.getDocuments().get(i);
                Map<String, DocumentField> receiptFields = analyzedReceipt.getFields();
                System.out.printf("----------- Analyzing receipt info %d -----------%n", i);
                DocumentField merchantNameField = receiptFields.get("MerchantName");
                if (merchantNameField != null) {
                    if (DocumentFieldType.STRING == merchantNameField.getType()) {
                        String merchantName = merchantNameField.getValueAsString();
                        System.out.printf("Merchant Name: %s, confidence: %.2f%n",
                                merchantName, merchantNameField.getConfidence());
                    }
                }

                DocumentField merchantPhoneNumberField = receiptFields.get("MerchantPhoneNumber");
                if (merchantPhoneNumberField != null) {
                    if (DocumentFieldType.PHONE_NUMBER == merchantPhoneNumberField.getType()) {
                        String merchantAddress = merchantPhoneNumberField.getValueAsPhoneNumber();
                        System.out.printf("Merchant Phone number: %s, confidence: %.2f%n",
                                merchantAddress, merchantPhoneNumberField.getConfidence());
                    }
                }

                DocumentField transactionDateField = receiptFields.get("TransactionDate");
                if (transactionDateField != null) {
                    if (DocumentFieldType.DATE == transactionDateField.getType()) {
                        LocalDate transactionDate = transactionDateField.getValueAsDate();
                        System.out.printf("Transaction Date: %s, confidence: %.2f%n",
                                transactionDate, transactionDateField.getConfidence());
                    }
                }

                var receiptItemsField = receiptFields.get("Items");
                if (receiptItemsField != null) {
                    System.out.printf("Receipt Items: %n");
                    if (DocumentFieldType.LIST == receiptItemsField.getType()) {
                        List<DocumentField> receiptItems = receiptItemsField.getValueAsList();
                        receiptItems.stream()
                                .filter(receiptItem -> DocumentFieldType.MAP == receiptItem.getType())
                                .map(documentField -> documentField.getValueAsMap())
                                .forEach(documentFieldMap -> documentFieldMap.forEach((key, documentField) -> {
                                    if ("Name".equals(key)) {
                                        if (DocumentFieldType.STRING == documentField.getType()) {
                                            String name = documentField.getValueAsString();
                                            System.out.printf("Name: %s, confidence: %.2fs%n",
                                                    name, documentField.getConfidence());
                                        }
                                    }
                                    if ("Quantity".equals(key)) {
                                        if (DocumentFieldType.DOUBLE == documentField.getType()) {
                                            Double quantity = documentField.getValueAsDouble();
                                            System.out.printf("Quantity: %f, confidence: %.2f%n",
                                                    quantity, documentField.getConfidence());
                                        }
                                    }
                                }));
                    }
                }
            }


        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        }
    }
}
