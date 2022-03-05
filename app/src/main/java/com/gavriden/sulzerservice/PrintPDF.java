package com.gavriden.sulzerservice;

import android.app.Service;
import com.gavriden.sulzerservice.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.Background;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.itextpdf.kernel.colors.ColorConstants.GREEN;
import static com.itextpdf.kernel.colors.ColorConstants.RED;
import static com.itextpdf.kernel.colors.ColorConstants.YELLOW;

public class PrintPDF extends Service {
    String pumpNumber;
    String reportData;
    String userName;
    String userSurname;
    String department;
    String customer;
    String pos;
    String size;
    String dis;
    String posnum;
    String plate;
    String levellubrication;
    String tds;
    String avdoc;
    String curve;
    String secdraw;
    String couplingsize;
    String spare;
    String couplingtype;
    String dimdraw;
    String couplinggap;
    String manual;
    String couplingscrew;
    String safety;
    String couplingalign;
    String certificate;
    String couplingguard;
    String genpipe;
    String installmotor;
    String bends;
    String bracketsmotor;
    String inletpipe;
    String shimsmotor;
    String locpipe;
    String supportpipe;
    String instalpipe;
    String painting;
    String gengrouting;
    String damages;
    String execution;
    String rotation;
    String grouting;
    String motorstand;
    String liftarm;
    String fasteners;
    String flanges;
    String gaskets;
    String typeseal;
    String swjoints;
    String swsettings;
    String swcontrolunit;
    Image image;
    Image mainImage;
    public static String mFilePath;
    Bitmap bitmap;
    Color STATUS;
    String typelubrication;
    String denis = "Denis";
    private static final String TAG = "myLogs";

    public PrintPDF(String pumpNumber, String reportData, String userName, String userSurname, Image image, Image mainImage, String department, String customer, String pos, String size, String dis,
                    String posnum, String typelubrication, String plate, String levellubrication, String avdoc, String tds, String curve, String secdraw, String couplingsize, String spare,
                    String couplingtype, String dimdraw, String couplinggap, String manual, String couplingscrew, String safety, String couplingalign, String certificate, String couplingguard,
                    String genpipe, String installmotor, String bends, String bracketsmotor, String inletpipe, String shimsmotor, String locpipe, String supportpipe, String instalpipe, String painting,
                    String gengrouting, String damages, String execution, String rotation, String grouting, String motorstand, String liftarm, String fasteners, String flanges, String gaskets,
                    String typeseal, String swjoints, String swsettings, String swcontrolunit) {
        this.pumpNumber = pumpNumber;
        this.reportData = reportData;
        this.userName = userName;
        this.userSurname = userSurname;
        this.image = image;
        this.mainImage = mainImage;
        this.department = department;
        this.customer = customer;
        this.pos = pos;
        this.size = size;
        this.dis = dis;
        this.posnum = posnum;
        this.typelubrication = typelubrication;
        this.plate = plate;
        this.levellubrication = levellubrication;
        this.avdoc = avdoc;
        this.tds = tds;
        this.curve = curve;
        this.secdraw = secdraw;
        this.couplingsize = couplingsize;
        this.spare = spare;
        this.couplingtype = couplingtype;
        this.dimdraw = dimdraw;
        this.couplinggap = couplinggap;
        this.manual = manual;
        this.couplingscrew = couplingscrew;
        this.safety = safety;
        this.couplingalign = couplingalign;
        this.certificate = certificate;
        this.couplingguard = couplingguard;
        this.genpipe = genpipe;
        this.installmotor = installmotor;
        this.bends = bends;
        this.bracketsmotor = bracketsmotor;
        this.inletpipe = inletpipe;
        this.shimsmotor = shimsmotor;
        this.locpipe = locpipe;
        this.supportpipe = supportpipe;
        this.instalpipe = instalpipe;
        this.painting = painting;
        this.gengrouting = gengrouting;
        this.damages = damages;
        this.execution = execution;
        this.rotation = rotation;
        this.grouting = grouting;
        this.motorstand = motorstand;
        this.liftarm = liftarm;
        this.fasteners = fasteners;
        this.flanges = flanges;
        this.gaskets = gaskets;
        this.typeseal = typeseal;
        this.swjoints = swjoints;
        this.swsettings = swsettings;
        this.swcontrolunit = swcontrolunit;
    }

    public void getPDF() throws IOException {

        String namePump = pumpNumber;
        String mFileName = new SimpleDateFormat("yyyyMMdd HHmmssSSS", Locale.getDefault()).format(System.currentTimeMillis());
        mFilePath = Environment.getExternalStorageDirectory() + "/" +namePump+"_"+mFileName + ".pdf";

        PdfDocument pdf = new PdfDocument(new PdfWriter(mFilePath));

        Document document = new Document(pdf);

        ArrayList<String> status = new ArrayList<String>();
        status.add(posnum.trim());
        status.add(typelubrication.trim());
        status.add(plate.trim());
        status.add(levellubrication.trim());
        status.add(avdoc.trim());
        status.add(tds.trim());
        status.add(curve.trim());
        status.add(secdraw.trim());
        status.add(couplingsize.trim());
        status.add(spare.trim());
        status.add(couplingtype.trim());
        status.add(dimdraw.trim());
        status.add(couplinggap.trim());
        status.add(manual.trim());
        status.add(couplingscrew.trim());
        status.add(safety.trim());
        status.add(couplingalign.trim());
        status.add(certificate.trim());
        status.add(couplingguard.trim());
        status.add(genpipe.trim());
        status.add(installmotor.trim());
        status.add(bends.trim());
        status.add(bracketsmotor.trim());
        status.add(inletpipe.trim());
        status.add(shimsmotor.trim());
        status.add(locpipe.trim());
        status.add(supportpipe.trim());
        status.add(instalpipe.trim());
        status.add(painting.trim());
        status.add(gengrouting.trim());
        status.add(damages.trim());
        status.add(execution.trim());
        status.add(rotation.trim());
        status.add(grouting.trim());
        status.add(motorstand.trim());
        status.add(liftarm.trim());
        status.add(fasteners.trim());
        status.add(flanges.trim());
        status.add(gaskets.trim());
        status.add(typeseal.trim());
        status.add(swjoints.trim());
        status.add(swsettings.trim());
        status.add(swcontrolunit.trim());

        if (status.contains("-")||status.contains("Problem")){
            STATUS = RED;
        }else if (status.contains("Comment")){
            STATUS = YELLOW;
        }else {
            STATUS = GREEN;
        }

        mainImage.setFixedPosition(1, 360, 595);
        mainImage.setWidth(155);
        document.add(mainImage);

        String FONT_FILENAME = "/assets/fonts/arialmt.ttf";
        String BOLD_FONT ="/assets/fonts/arialmtbolbold.ttf";
        PdfFont font = PdfFontFactory.createFont(FONT_FILENAME, PdfEncodings.IDENTITY_H);
        PdfFont fontB = PdfFontFactory.createFont(BOLD_FONT, PdfEncodings.IDENTITY_H);

        document.setFont(font);

        float columnWidth1[] = {138, 2, 135, 10, 138, 2, 135};
        Table table1 = new Table(columnWidth1);
        Table table2 = new Table(columnWidth1);

        image.setWidth(130);

        //mainImage.setWidth(100);

        table1.addCell(new Cell(1,4).setVerticalAlignment(VerticalAlignment.MIDDLE).add(new Paragraph("INSTALLATION SUPERVISION REPORT").setFont(fontB).setFontSize(14)).setBorder(Border.NO_BORDER));
        //table1.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("").setFontSize(10)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(image.setAutoScale(true)).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell(1, 7).add(new Paragraph("").setFontSize(5)).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell(1,6).add(new Paragraph("").setFontSize(10)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().setBackgroundColor(STATUS).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("Status").setFontSize(10)));

        table1.addCell(new Cell(1, 7).add(new Paragraph("").setFontSize(5)).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Issues by:").setFontSize(9)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph(userName + "" + userSurname).setFontSize(9)).setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        //table1.addCell(new Cell(9, 3).add(mainImage.setMargins(0,0,0,0)));
        //table1.addCell(new Cell(9, 3).setVerticalAlignment(VerticalAlignment.MIDDLE).add(mainImage.setHorizontalAlignment(HorizontalAlignment.CENTER.CENTER)));
        table1.addCell(new Cell(9, 3).add(new Paragraph("")));

        table1.addCell(new Cell().add(new Paragraph("Date:").setFontSize(9)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph(reportData).setFontSize(9)).setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().setMarginBottom(10).add(new Paragraph("Company name:").setMarginTop(10).setFontSize(9)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().setMarginBottom(10).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().setMarginBottom(10).add(new Paragraph(customer).setMarginTop(10).setFontSize(9)).setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER));
        table1.addCell(new Cell().setMarginBottom(10).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("Department:").setFontSize(9)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph(department).setFontSize(9)).setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell().add(new Paragraph("").setMarginTop(10).setFontSize(9)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell(1,2).setBackgroundColor(ColorConstants.LIGHT_GRAY).add(new Paragraph("Pump type:").setFontSize(10)));
        table1.addCell(new Cell().setBackgroundColor(ColorConstants.LIGHT_GRAY).add(new Paragraph(""+"A "+size+" - "+dis).setFontSize(10)));
        table1.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell(1,2).setBackgroundColor(ColorConstants.LIGHT_GRAY).add(new Paragraph("Serial number:").setFontSize(10)));
        table1.addCell(new Cell().setBackgroundColor(ColorConstants.LIGHT_GRAY).add(new Paragraph(""+pumpNumber).setFontSize(10)));
        table1.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table1.addCell(new Cell(1,2).setBackgroundColor(ColorConstants.LIGHT_GRAY).add(new Paragraph("Position:").setFontSize(10)));
        table1.addCell(new Cell().setBackgroundColor(ColorConstants.LIGHT_GRAY).add(new Paragraph(""+pos).setFontSize(10)));
        table1.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("1. Documentation").setMarginTop(5).setFont(fontB).setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("Comments").setMarginTop(5).setFont(fontB).setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("6. Lubrication").setMarginTop(5).setFont(fontB).setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("Comments").setMarginTop(5).setFont(fontB).setFontSize(9)).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("1.1 Pos / Serial number").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(posnum).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph(String.valueOf("6.1 Lubrication type")).setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(typelubrication).setFontSize(9)));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("1.2 Machine plate").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(plate).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setPaddings(0,2, 0, 2).add(new Paragraph("6.2 Grease fill / Oil level").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(levellubrication).setFontSize(9)));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("1.3 Availability of doc(s)").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(avdoc).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("1.4 Technical data sheet").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(tds).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("1.5 Performance curve").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(curve).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("7.Shaft coupling").setFont(fontB).setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("1.6 Sectional drawings").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(secdraw).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("7.1 Coupling size").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(couplingsize).setFontSize(9)));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("1.7 Spare parts list").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(spare).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("7.2 Type").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(couplingtype).setFontSize(9)));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("1.8 Dimension drawings").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(dimdraw).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("7.3 Gap for spacer").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(couplinggap).setFontSize(9)));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("1.9 O & M manual").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(manual).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("7.4 Locking screws").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(couplingscrew).setFontSize(9)));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("1.10 Safety instructions").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(safety).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("7.5 Alignment").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(couplingalign).setFontSize(9)));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("1.11 CE-certificate").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(certificate).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("7.6 Coupling guard").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(couplingguard).setFontSize(9)));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("2. Pipe-works").setMarginTop(3).setFont(fontB).setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("").setMarginTop(3).setFont(fontB).setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("8. Motor").setMarginTop(3).setFont(fontB).setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("").setMarginTop(3).setFont(fontB).setFontSize(9)).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("2.1 General piping").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(genpipe).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("8.1 Installation").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(installmotor).setFontSize(9)));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("2.2 Bends").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(bends).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("8.2 Motor brackets").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(bracketsmotor).setFontSize(9)));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("2.3 Inlet pipe").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(inletpipe).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("8.3 Shims").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(shimsmotor).setFontSize(9)));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("2.4 Location and type of valves").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(locpipe).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("2.5 Pipe supports").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(supportpipe).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("9. General conditions").setFont(fontB).setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("2.6 Installation vs tank/agitator").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(instalpipe).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("9.1 Painting").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(painting).setFontSize(9)));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("9.2 Grouting ").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(gengrouting).setFontSize(9)));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("3 Foundation / baseplate").setFont(fontB).setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("9.3 Visual damages").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(damages).setFontSize(9)));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("3.1 Execution").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(execution).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("9.4 Rotation by hand").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(rotation).setFontSize(9)));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("3.2 Grouting").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(grouting).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("3.3 Motor stand").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(motorstand).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell(1,3).setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("Other comments:").setFont(fontB).setFontSize(9)).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("3.4 Lifting arm bracket").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(liftarm).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell(10,3).setTextAlignment(TextAlignment.CENTER).setPaddings(0,2, 0, 2).add(new Paragraph("").setFont(fontB).setFontSize(9)));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("4. Flanges").setMarginTop(3).setFont(fontB).setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("4.1 Fasteners").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(fasteners).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("4.2 Flanges").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(flanges).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("4.3 Gaskets").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(gaskets).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("5. Shaft seal").setMarginTop(3).setFont(fontB).setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph("").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("5.1 Type").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(typeseal).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("5.2 Sealing water joints").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(swjoints).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("5.3 Sealing water settings").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(swsettings).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));

        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("5.4 Sealing water control unit").setFontSize(9)).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(swcontrolunit).setFontSize(9)));
        table2.addCell(new Cell().setPaddings(0,2, 0, 2).add(new Paragraph("")).setBorder(Border.NO_BORDER));



        document.add(table1);
        document.add(table2);
        document.close();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }


}
