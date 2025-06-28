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
        contactList.add(new Contact("Dr. John Doe", "Department Head & Chief Instructor", "info@cs.edu", "01911223344", "CMT-302"));
        contactList.add(new Contact("Anika Sultana", "Senior Lecturer", "anika@cs.edu", "01755667788", "CMT-204"));
        contactList.add(new Contact("Md. Hafizur Rahman", "Lecturer", "hafiz@cs.edu", "01677889900", "CMT-205"));

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