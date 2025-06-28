package com.example.dms2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ContactUs extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_us);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        List<Contact> contactList = new ArrayList<>();
        contactList.add(new Contact("Bithi Biswas", "Chief Instructor & Department Head (2nd Shift), Computer Science and Technology", "bithibiswas98@gmail.com", "01749485453", "3rd Floor"));
        contactList.add(new Contact("Md. Mehedi Hasan", "Instructor (Tech), Computer", "mehedi.crux@gmail.com", "01737232587", "2nd Floor | Beside Digital Lab"));
        contactList.add(new Contact("Md. Kamal Sowdagar", "Instructor (Tech), Computer", "kamalsowdagar@gmail.com", "01521412326", "2nd Floor | Beside Digital Lab"));
        contactList.add(new Contact("Tamanna Halim Shanta", "Junior Instructor (Tech/Computer)", "tamannashanta5@gmail.com", "01767177617", "3rd Floor | West Side"));
        contactList.add(new Contact("Shyamananda Pal", "Junior Instructor (Tech), Computer", "shamanando@gmail.com", "01558957892", "3rd Floor | West Side"));
        contactList.add(new Contact("Sajib Kumar", "Junior Instructor (Tech/Computer)", "sajib0430@gmail.com", "01730684022", "3rd Floor | West Side"));



        // 2. Render all contacts
        for (Contact contact : contactList) {
            addContactCard(contact);
        }
    }

    private void addContactCard(Contact contact) {
        LinearLayout contact_card_container = findViewById(R.id.contact_card_container);
        View view = LayoutInflater.from(this).inflate(R.layout.department_contact_card, contact_card_container, false);

        TextView title = view.findViewById(R.id.department_contact_card_title);
        TextView role = view.findViewById(R.id.department_contact_card_position);
        TextView emailView = view.findViewById(R.id.department_contact_card_phone_email);
        TextView phoneView = view.findViewById(R.id.department_contact_card_phone);
        TextView officeView = view.findViewById(R.id.department_contact_card_office);

        title.setText(contact.name);
        role.setText(contact.position);
        emailView.setText(contact.email);
        phoneView.setText(contact.phone);
        officeView.setText(contact.office);

        contact_card_container.addView(view);
    }
}


class Contact {
    public String name;
    public String position;
    public String email;
    public String phone;
    public String office;

    public Contact(String name, String position, String email, String phone, String office) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.office = office;
    }
}