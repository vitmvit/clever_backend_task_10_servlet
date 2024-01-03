package org.example.constant;

import com.itextpdf.text.Font;

public class Constant {

    // cache
    public static final String LRU = "lru";
    public static final String LFU = "lfu";
    public static final float LOAD_FACTOR = 1.0f;
    public static final float CAPACITY_FACTOR = 1.5f;

    // pdf
    public static final String DIRECTORY_REPORT = "report";
    public static final String DIRECTORY_CHECK = "check";
    public static final String BACKGROUND_IMAGE = "report_background/Clevertec_Template.jpg";
    public static final String FILE_BASE_REPORT = DIRECTORY_REPORT + "/report_";
    public static final String FILE_BASE_CHECK = DIRECTORY_CHECK + "/check_";
    public static final String PDF = ".pdf";
    public static final Font FONT_TITLE = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
    public static final Font FONT_DATA = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
    public static final Font FONT_BODY = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

    // pagination
    public static final int PAGE_SIZE = 20;
    public static final int PAGE_NUMBER = 1;

    // error
    public static final String CAT_NOT_FOUND_ERROR = "Cat not found";
    public static final String RETRIEVING_ERROR = "Error retrieving cats list";
    public static final String INVALID_REQUEST_PARAMETERS_ERROR = "Invalid request parameters";
    public static final String CAT_CREATED_ERROR = "Cat created error";
    public static final String CAT_UPDATED_ERROR = "Cat updated error";
    public static final String CAT_DELETED_ERROR = "Cat deleted error";
    public static final String CAT_IS_NULL_ERROR = "Cat is null";

    // message
    public static final String CAT_IS_DELETED_MESSAGE = "Cat is deleted";
    public static final String NO_SUCH_DATA_MESSAGE = "No such data";
    public static final String CHECK_WAS_CREATED_MESSAGE = "Check was created";
}
