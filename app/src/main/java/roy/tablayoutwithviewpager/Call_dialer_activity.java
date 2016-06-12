package roy.tablayoutwithviewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.microsoft.windowsazure.mobileservices.*;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.net.MalformedURLException;

public class Call_dialer_activity extends AppCompatActivity {


   private MobileServiceClient mClient;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_call_dialer_activity);
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
      toolbar.setNavigationOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            goback();
         }
      });

      try {
         mClient = new MobileServiceClient(
                 "https://alqulator.azurewebsites.net",
                 this
         );
      } catch (MalformedURLException e) {
         e.printStackTrace();
      }


      TodoItem item = new TodoItem();
      item.Text = "Awesome item";
      mClient.getTable(TodoItem.class).insert(item, new TableOperationCallback<TodoItem>() {
         public void onCompleted(TodoItem entity, Exception exception, ServiceFilterResponse response) {
            if (exception == null) {
               // Insert succeeded
            } else {
               // Insert failed
            }
         }
      });

   }
   private void goback() {
      startActivity(new Intent(this, MainActivity.class));
      finish();
   }
}
