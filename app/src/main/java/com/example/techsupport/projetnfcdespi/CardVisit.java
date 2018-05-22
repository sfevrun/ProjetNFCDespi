package com.example.techsupport.projetnfcdespi;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CardVisit extends AppCompatActivity  implements NfcAdapter.CreateNdefMessageCallback,
        NfcAdapter.OnNdefPushCompleteCallback {
    private NfcAdapter nfcAdapter = null;
    private static final int MESSAGE_SENT = 1;
    private RecyclerView rvCards;
    private List<CarteDespi> cards;
    public TextView txtprenom;
    public TextView txtnom;
    public TextView txtfonction;
    public TextView tel;
    public TextView type;
    CarteDespi card;
    public TextView email;
    CardAdapter cardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_visit);
        FlowManager.init(new FlowConfig.Builder(this).build());

        card= SQLite.select().
                from(CarteDespi.class).
                where(CarteDespi_Table.type.is(2))
                .querySingle();




        rvCards = (RecyclerView) findViewById(R.id.rvCard);
        cards =new ArrayList<CarteDespi>();
        cardAdapter = new CardAdapter(this,cards);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvCards.setLayoutManager(linearLayoutManager);
        rvCards.setAdapter( cardAdapter);

        populatecard();

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {

            Toast.makeText(getBaseContext(),"NFC is not available on this device",Toast.LENGTH_LONG).show();
        }
        // Register callback to set NDEF message
        nfcAdapter.setNdefPushMessageCallback(this, this);
        // Register callback to listen for message-sent success
        nfcAdapter.setOnNdefPushCompleteCallback(this, this);




    }
    @Override
    protected void onPause() {
        super.onPause();
        if(nfcAdapter!= null)
            nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        setIntent(intent);

    }
    @Override

    public void onResume() {
        super.onResume();
        //initialisation
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());
        }


    }
    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {

        Gson cardjson = new Gson();
        CarteDespi card= SQLite.select().
                from(CarteDespi.class).
                where(CarteDespi_Table.type.is(2)).querySingle();

        cardjson.toJson(card);

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
               // updateFragments();
            }
        });


        NdefMessage msg = new NdefMessage(
                new NdefRecord[] { createMimeRecord(
                        "application/edu.cs4730.nfcdemo.beam",  cardjson.toJson(card).getBytes())
                });


        return msg;
    }

    @Override
    public void onNdefPushComplete(NfcEvent arg0) {

        mHandler.obtainMessage(MESSAGE_SENT).sendToTarget();
    }

    /** This handler receives a message from onNdefPushComplete */
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_SENT:
                    Toast.makeText(getApplicationContext(), "Message sent!", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };


    void processIntent(Intent intent) {
        Toast.makeText(getApplicationContext(), "Message receive!", Toast.LENGTH_LONG).show();
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        // only one message sent during the beam
        NdefMessage msg = (NdefMessage) rawMsgs[0];

        // record 0 contains the MIME type, record 1 is the AAR, if present
        String test =new String(msg.getRecords()[0].getPayload());
        // Toast.makeText(this, " 22"+test, Toast.LENGTH_LONG).show();

        Gson objectjson = new Gson();
        try {
            CarteDespi card = objectjson.fromJson(test,CarteDespi.class);





        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

    public NdefRecord createMimeRecord(String mimeType, byte[] payload) {
        byte[] mimeBytes = mimeType.getBytes(Charset.forName("US-ASCII"));
        NdefRecord mimeRecord = new NdefRecord(
                NdefRecord.TNF_MIME_MEDIA, mimeBytes, new byte[0], payload);
        return mimeRecord;
    }

    private void populatecard() {
       // CarteDespi carte =new CarteDespi
        txtprenom = (TextView) findViewById(R.id.txtprenom);
        txtnom = (TextView) findViewById(R.id.txtnom);

        txtfonction = (TextView) findViewById(R.id.txtfonction);
        tel = (TextView) findViewById(R.id.textView10);
        //type = (TextView)findViewById(R.id.txtTypel);
        email = (TextView) findViewById(R.id.textView9);
        //cards = new VisitingCard();
        if(card==null) {

           CarteDespi carte = new CarteDespi(txtprenom.getText().toString(), txtnom.getText().toString(), txtfonction.getText().toString(), email.getText().toString(), tel.getText().toString(), 2);
       carte.save();
        }
        else{
            txtprenom.setText(card.getFirstname());
            txtnom.setText(card.getLastName());

            txtfonction.setText(card.getFonction());
            tel.setText(card.getCellphone());
            //type = (TextView)findViewById(R.id.txtTypel);
            email.setText(card.getCourriel());

        }
        cards= SQLite.select().
                from(CarteDespi.class).queryList();
        cardAdapter.notifyDataSetChanged();

    }

}
