package hw2;

import java.util.Arrays;
import java.util.List;

public interface TestData {

    List<String> navigation = Arrays.asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");
    int expectedImages = 4;
    List<String> textOfImages = Arrays.asList(
            "To include good practices\n" +
                    "and ideas from successful\n" +
                    "EPAM project",
            "To be flexible and\n" +
                    "customizable",
            "To be multiplatform",
            "Already have good base\n" +
                    "(about 20 internal and\n" +
                    "some external projects),\n" +
                    "wish to get more…");
    String mainTitle = "EPAM FRAMEWORK WISHES…";
    String mainText = "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, " +
            "SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. " +
            "UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI " +
            "UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT " +
            "IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.";
    String subHeader = "JDI GITHUB";
    String JDI_GITHUB_URL = "https://jdi-testing.github.io/jdi-light/index.html";
    String name = "ROMAN IOVLEV";
    String title = "Home Page";
}