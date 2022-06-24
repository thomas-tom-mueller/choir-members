package com.majtom.choirmembers.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Validated
@Table(name = "members")

@Entity
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private Long id;

    private String Abteilung;

    private Integer Aktiv;

    @Column(length = 25)
    private String Anrede;

    private Integer Austritt;

    @Column(length = 40)
    private String Bank;

    @Column(length = 4000)
    private String Bemerk;

    private Integer Blz;

    @Column(length = 4000)
    private String Ehrungen;

    private String Email;

    @Column(length = 25)
    private String Famstand;

    @Column(length = 25)
    private String Funktion;

    //@Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date Geburt;

    @Column(length = 25)
    private String Ktonr;

    //@Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date Lkontakt;

    private Integer Mitnum;

    //@Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date Mitseit;

    @Column(length = 25)
    private String Mobil;

    @NotBlank(message = "Vorname is mandatory")
    @Column(length = 25)
    private String Name;

    @Column(length = 25)
    private String Status;

    @Column(length = 40)
    private String Ort;

    private Integer Plz;

    @Column(length = 40)
    private String Post3;

    @Column(length = 40)
    private String Strasse;

    @Column(length = 25)
    private String Telefon;

    @Column(length = 25)
    private String Telefon2;

    @NotBlank(message = "Vorname is mandatory")
    @Column(length = 25)
    private String Vorname;

    @Column(length = 25)
    private String Stimme;

    //@Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date Zu10;

    //@Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date Zu11;

    //@Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date Zu2;

    @Column(length = 40)
    private String Zu3;

    @Column(length = 40)
    private String Zu5;

    @Column(length = 40)
    private String Zu7;
}