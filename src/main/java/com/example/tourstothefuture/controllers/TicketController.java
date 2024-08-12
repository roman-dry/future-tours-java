package com.example.tourstothefuture.controllers;

import com.example.tourstothefuture.requests.UserRequest;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class TicketController {

    @CrossOrigin("http://localhost:3000")
    @PostMapping ("api/auth/generateTicket")
    public ResponseEntity<byte[]> generatePdf(@RequestBody UserRequest userRequest) {

        System.out.println(userRequest.getCentury());
        System.out.println(userRequest.getTransports());
        System.out.println(userRequest.getRobots());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (InputStream templateInputStream = new ClassPathResource("templates/Ticket_to_the_future.pdf").getInputStream();
             PdfReader pdfReader = new PdfReader(templateInputStream);
             PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
             PdfDocument pdfDocument = new PdfDocument(pdfReader, pdfWriter);
             Document document = new Document(pdfDocument)) {

            // Налаштування шрифта
            /*PdfFont font = PdfFontFactory.createFont();*/
            PdfFont font = PdfFontFactory.createFont("fonts/Leaguespartanbold.ttf", PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);

            // Додаємо ім'я до існуючого PDF
            Paragraph paragraph1 = new Paragraph(userRequest.getName())
                    .setFont(font)
                    .setFontSize(60)
                    .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                    .setFixedPosition(1, 80, 400, 750)
                    .setTextAlignment(TextAlignment.LEFT);

            document.add(paragraph1);

            Paragraph paragraph2 = new Paragraph(userRequest.getSurname())
                    .setFont(font)
                    .setFontSize(60)
                    .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                    .setFixedPosition(1, 80, 330, 750)
                    .setTextAlignment(TextAlignment.LEFT);

            document.add(paragraph2);

            Paragraph paragraph3 = new Paragraph(userRequest.getCentury())
                    .setFont(font)
                    .setFontSize(36)
                    .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                    .setFixedPosition(1, 611, 158, 130)
                    .setTextAlignment(TextAlignment.LEFT);

            document.add(paragraph3);

            String transport1 = "";
            String transport2 = "";
            String transport3 = "";
            String transport4 = "";
            String transport5 = "";
            String transport6 = "";
            String transport7 = "";
            String transport8 = "";
            String transport9 = "";
            String transport10 = "";
            String transport11 = "";
            String transport12 = "";

            List<String> transports = userRequest.getTransports();

            long nonEmptyValuesOfTransports = transports.stream()
                    .filter(transport -> transport != null && !transport.trim().isEmpty())
                    .count();

            if (userRequest.getTransports().size() != 0) {
                if (userRequest.getTransports().get(0) != null) {
                    transport1 = userRequest.getTransports().get(0);
                    if (nonEmptyValuesOfTransports < 5 && nonEmptyValuesOfTransports != 0) {
                        Paragraph paragraph5 = new Paragraph(transport1)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 560, 530, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph5);

                    } else if (nonEmptyValuesOfTransports > 4 && nonEmptyValuesOfTransports < 9) {
                        Paragraph paragraph5 = new Paragraph(transport1)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 355, 530, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph5);

                    } else if (nonEmptyValuesOfTransports > 8) {
                        Paragraph paragraph5 = new Paragraph(transport1)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 175, 530, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph5);

                    }

                }
                if (userRequest.getTransports().get(1) != null) {
                    transport2 = userRequest.getTransports().get(1);
                    if (nonEmptyValuesOfTransports < 5 && nonEmptyValuesOfTransports != 0) {
                        Paragraph paragraph6 = new Paragraph(transport2)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 560, 500, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph6);

                    } else if (nonEmptyValuesOfTransports > 4 && nonEmptyValuesOfTransports < 9) {
                        Paragraph paragraph6 = new Paragraph(transport2)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 355, 500, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph6);
                    } else if (nonEmptyValuesOfTransports > 8) {
                        Paragraph paragraph6 = new Paragraph(transport2)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 175, 500, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph6);

                    }

                }
                if (userRequest.getTransports().get(2) != null) {
                    transport3 = userRequest.getTransports().get(2);
                    if (nonEmptyValuesOfTransports < 5 && nonEmptyValuesOfTransports != 0) {
                        Paragraph paragraph7 = new Paragraph(transport3)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 560, 470, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph7);

                    } else if (nonEmptyValuesOfTransports > 4 && nonEmptyValuesOfTransports < 9) {
                        Paragraph paragraph7 = new Paragraph(transport3)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 355, 470, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph7);
                    } else if (nonEmptyValuesOfTransports > 8) {
                        Paragraph paragraph7 = new Paragraph(transport3)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 175, 470, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph7);
                    }

                }
                if (userRequest.getTransports().get(3) != null) {
                    transport4 = userRequest.getTransports().get(3);
                    if (nonEmptyValuesOfTransports < 5 && nonEmptyValuesOfTransports != 0) {
                        Paragraph paragraph8 = new Paragraph(transport4)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 560, 440, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph8);
                    } else if (nonEmptyValuesOfTransports > 4 && nonEmptyValuesOfTransports < 9) {
                        Paragraph paragraph8 = new Paragraph(transport4)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 355, 440, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph8);
                    } else if (nonEmptyValuesOfTransports > 8) {
                        Paragraph paragraph8 = new Paragraph(transport4)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 175, 440, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph8);

                    }

                }
                if (userRequest.getTransports().get(4) != null) {
                    transport5 = userRequest.getTransports().get(4);
                    if (nonEmptyValuesOfTransports > 4 && nonEmptyValuesOfTransports < 9) {
                        Paragraph paragraph9 = new Paragraph(transport5)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 770, 530, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph9);
                    } else if (nonEmptyValuesOfTransports > 8) {
                        Paragraph paragraph9 = new Paragraph(transport5)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 560, 530, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph9);
                    }

                }
                if (userRequest.getTransports().get(5) != null) {
                    transport6 = userRequest.getTransports().get(5);
                    if (nonEmptyValuesOfTransports > 4 && nonEmptyValuesOfTransports < 9) {
                        Paragraph paragraph10 = new Paragraph(transport6)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 770, 500, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph10);
                    } else if (nonEmptyValuesOfTransports > 8) {
                        Paragraph paragraph10 = new Paragraph(transport6)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 560, 500, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph10);
                    }

                }
                if (userRequest.getTransports().get(6) != null) {
                    transport7 = userRequest.getTransports().get(6);
                    if (nonEmptyValuesOfTransports > 4 && nonEmptyValuesOfTransports < 9) {
                        Paragraph paragraph11 = new Paragraph(transport7)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 770, 470, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph11);
                    } else if (nonEmptyValuesOfTransports > 8) {
                        Paragraph paragraph11 = new Paragraph(transport7)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 560, 470, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph11);
                    }

                }
                if (userRequest.getTransports().get(7) != null) {
                    transport8 = userRequest.getTransports().get(7);
                    if (nonEmptyValuesOfTransports > 4 && nonEmptyValuesOfTransports < 9) {
                        Paragraph paragraph12 = new Paragraph(transport8)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 770, 440, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph12);
                    } else if (nonEmptyValuesOfTransports > 8) {
                        Paragraph paragraph12 = new Paragraph(transport8)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 560, 440, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph12);
                    }

                }
                if (userRequest.getTransports().get(8) != null) {
                    transport9 = userRequest.getTransports().get(8);
                    if (nonEmptyValuesOfTransports > 8) {
                        Paragraph paragraph13 = new Paragraph(transport9)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 940, 530, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph13);
                    }

                }
                if (userRequest.getTransports().get(9) != null) {
                    transport10 = userRequest.getTransports().get(9);
                    if (nonEmptyValuesOfTransports > 8) {
                        Paragraph paragraph14 = new Paragraph(transport10)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 940, 500, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph14);
                    }

                }
                if (userRequest.getTransports().get(10) != null) {
                    transport11 = userRequest.getTransports().get(10);
                    if (nonEmptyValuesOfTransports > 8) {
                        Paragraph paragraph15 = new Paragraph(transport11)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 940, 470, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph15);
                    }

                }
                if (userRequest.getTransports().get(11) != null) {
                    transport12 = userRequest.getTransports().get(11);
                    if (nonEmptyValuesOfTransports > 8) {
                        Paragraph paragraph16 = new Paragraph(transport12)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 940, 440, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph16);
                    }

                }

            }

            String robot1 = "";
            String robot2 = "";
            String robot3 = "";
            String robot4 = "";
            String robot5 = "";
            String robot6 = "";
            String robot7 = "";
            String robot8 = "";
            String robot9 = "";
            String robot10 = "";
            String robot11 = "";
            String robot12 = "";

            List<String> robots = userRequest.getRobots();

            long nonEmptyValuesOfRobots = robots.stream()
                    .filter(robot -> robot != null && !robot.trim().isEmpty())
                    .count();

            if (userRequest.getRobots().size() != 0) {
                if (userRequest.getRobots().get(0) != null) {
                    robot1 = userRequest.getRobots().get(0);
                    if (nonEmptyValuesOfRobots < 5 && nonEmptyValuesOfRobots != 0) {
                        Paragraph paragraph17 = new Paragraph(robot1)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 560, 260, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph17);

                    } else if (nonEmptyValuesOfRobots > 4 && nonEmptyValuesOfRobots < 9) {
                        Paragraph paragraph17 = new Paragraph(robot1)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 355, 260, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph17);

                    } else if (nonEmptyValuesOfRobots > 8) {
                        Paragraph paragraph17 = new Paragraph(robot1)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 175, 260, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph17);

                    }

                }

                if (userRequest.getRobots().get(1) != null) {
                    robot2 = userRequest.getRobots().get(1);
                    if (nonEmptyValuesOfRobots < 5 && nonEmptyValuesOfRobots != 0) {
                        Paragraph paragraph18 = new Paragraph(robot2)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 560, 230, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph18);

                    } else if (nonEmptyValuesOfRobots > 4 && nonEmptyValuesOfRobots < 9) {
                        Paragraph paragraph18 = new Paragraph(robot2)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 355, 230, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph18);
                    } else if (nonEmptyValuesOfRobots > 8) {
                        Paragraph paragraph18 = new Paragraph(robot2)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 175, 230, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph18);

                    }

                }
                if (userRequest.getRobots().get(2) != null) {
                    robot3 = userRequest.getRobots().get(2);
                    if (nonEmptyValuesOfRobots < 5 && nonEmptyValuesOfRobots != 0) {
                        Paragraph paragraph19 = new Paragraph(robot3)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 560, 200, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph19);

                    } else if (nonEmptyValuesOfRobots > 4 && nonEmptyValuesOfRobots < 9) {
                        Paragraph paragraph19 = new Paragraph(robot3)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 355, 200, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph19);
                    } else if (nonEmptyValuesOfRobots > 8) {
                        Paragraph paragraph19 = new Paragraph(robot3)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 175, 200, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph19);
                    }

                }
                if (userRequest.getRobots().get(3) != null) {
                    robot4 = userRequest.getRobots().get(3);
                    if (nonEmptyValuesOfRobots < 5 && nonEmptyValuesOfRobots != 0) {
                        Paragraph paragraph20 = new Paragraph(robot4)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 560, 170, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph20);
                    } else if (nonEmptyValuesOfRobots > 4 && nonEmptyValuesOfRobots < 9) {
                        Paragraph paragraph20 = new Paragraph(robot4)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 355, 170, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph20);
                    } else if (nonEmptyValuesOfRobots > 8) {
                        Paragraph paragraph20 = new Paragraph(robot4)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 175, 170, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph20);

                    }

                }
                if (userRequest.getRobots().get(4) != null) {
                    robot5 = userRequest.getRobots().get(4);
                    if (nonEmptyValuesOfRobots > 4 && nonEmptyValuesOfRobots < 9) {
                        Paragraph paragraph21 = new Paragraph(robot5)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 770, 260, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph21);
                    } else if (nonEmptyValuesOfRobots > 8) {
                        Paragraph paragraph21 = new Paragraph(robot5)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 560, 260, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph21);
                    }

                }
                if (userRequest.getRobots().get(5) != null) {
                    robot6 = userRequest.getRobots().get(5);
                    if (nonEmptyValuesOfRobots > 4 && nonEmptyValuesOfRobots < 9) {
                        Paragraph paragraph22 = new Paragraph(robot6)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 770, 230, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph22);
                    } else if (nonEmptyValuesOfRobots > 8) {
                        Paragraph paragraph22 = new Paragraph(robot6)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 560, 230, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph22);
                    }

                }
                if (userRequest.getRobots().get(6) != null) {
                    robot7 = userRequest.getRobots().get(6);
                    if (nonEmptyValuesOfRobots > 4 && nonEmptyValuesOfRobots < 9) {
                        Paragraph paragraph23 = new Paragraph(robot7)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 770, 200, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph23);
                    } else if (nonEmptyValuesOfRobots > 8) {
                        Paragraph paragraph23 = new Paragraph(robot7)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 560, 200, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph23);
                    }

                }
                if (userRequest.getRobots().get(7) != null) {
                    robot8 = userRequest.getRobots().get(7);
                    if (nonEmptyValuesOfRobots > 4 && nonEmptyValuesOfRobots < 9) {
                        Paragraph paragraph24 = new Paragraph(robot8)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 770, 170, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph24);
                    } else if (nonEmptyValuesOfRobots > 8) {
                        Paragraph paragraph24 = new Paragraph(robot8)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 560, 170, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph24);
                    }

                }
                if (userRequest.getRobots().get(8) != null) {
                    robot9 = userRequest.getRobots().get(8);
                    if (nonEmptyValuesOfRobots > 8) {
                        Paragraph paragraph25 = new Paragraph(robot9)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 940, 260, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph25);
                    }

                }
                if (userRequest.getRobots().get(9) != null) {
                    robot10 = userRequest.getRobots().get(9);
                    if (nonEmptyValuesOfRobots > 8) {
                        Paragraph paragraph26 = new Paragraph(robot10)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 940, 230, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph26);
                    }

                }
                if (userRequest.getRobots().get(10) != null) {
                    robot11 = userRequest.getRobots().get(10);
                    if (nonEmptyValuesOfRobots > 8) {
                        Paragraph paragraph27 = new Paragraph(robot11)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 940, 200, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph27);
                    }

                }
                if (userRequest.getRobots().get(11) != null) {
                    robot12 = userRequest.getRobots().get(11);
                    if (nonEmptyValuesOfRobots > 8) {
                        Paragraph paragraph28 = new Paragraph(robot12)
                                .setFont(font)
                                .setFontSize(16)
                                .setFontColor(new DeviceRgb(0xff, 0xd7, 0x00))
                                .setFixedPosition(2, 940, 170, 400)
                                .setTextAlignment(TextAlignment.LEFT);

                        document.add(paragraph28);
                    }

                }


            }


            /*Paragraph paragraph2 = new Paragraph(userRequest.getTransport())
                    .setFont(font)
                    .setFontSize(16)
                    .setFontColor(ColorConstants.WHITE)
                    .setFixedPosition(2, 240, pdfDocument.getPage(1).getPageSize().getHeight() - 300, 200)
                    .setTextAlignment(TextAlignment.LEFT);

            document.add(paragraph2);*/

        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] pdfBytes = byteArrayOutputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "modified_template.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}

