package FormRecognizer;
import com.azure.ai.formrecognizer.FormRecognizerClient;
import com.azure.ai.formrecognizer.FormRecognizerClientBuilder;
import com.azure.ai.formrecognizer.documentanalysis.DocumentAnalysisClient;
import com.azure.ai.formrecognizer.documentanalysis.DocumentAnalysisClientBuilder;
import com.azure.ai.formrecognizer.documentanalysis.models.AnalyzeDocumentOptions;
import com.azure.ai.formrecognizer.documentanalysis.models.AnalyzeResult;
import com.azure.ai.formrecognizer.documentanalysis.models.DocumentTable;
import com.azure.ai.formrecognizer.documentanalysis.models.OperationResult;
import com.azure.ai.formrecognizer.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.polling.SyncPoller;

import java.io.*;
import java.util.*;

public class FormRecognizer {
    private final static String ENDPOINT = "https://form-recognizer-techchallenge.cognitiveservices.azure.com/";
    private final static String API_KEY = "c13b6ddbc6044ff0a0d98a3463f9bc64";

    public static void main(String[] args) {
        try {
            DocumentAnalysisClient client = new DocumentAnalysisClientBuilder()
                    .credential(new AzureKeyCredential(API_KEY))
                    .endpoint(ENDPOINT)
                    .buildClient();

            String documentUrl = "https://static.euronews.com/articles/stories/06/35/53/24/1440x810_cmsv2_548e11b5-0a57-53f4-88d9-5ea703413300-6355324.jpg";
            String modelId = "prebuilt-idDocument";
            SyncPoller<OperationResult, AnalyzeResult > analyzeDocumentPoller =
                    client.beginAnalyzeDocumentFromUrl(modelId, documentUrl);

            AnalyzeResult analyzeResult = analyzeDocumentPoller.getFinalResult();


            // Key-value pairs
            analyzeResult.getKeyValuePairs().forEach(documentKeyValuePair -> {
                System.out.printf("Key content: %s%n", documentKeyValuePair.getKey().getContent());
                System.out.printf("Key content bounding region: %s%n",
                        documentKeyValuePair.getKey().getBoundingRegions().toString());
                if (documentKeyValuePair.getValue() != null) {
                    System.out.printf("Value content: %s%n", documentKeyValuePair.getValue().getContent());
                    System.out.printf("Value content bounding region: %s%n", documentKeyValuePair.getValue().getBoundingRegions().toString());
                }
            });




        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        }
    }
}
