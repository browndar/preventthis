package org.catdragon.preventthis;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GoogleApiTest {
    @Test
    public void testAPIKey() {
        String googleAppCreds = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
        assertThat(googleAppCreds, is(not(nullValue())));
        assertThat(googleAppCreds, is(not(equalTo(""))));
    }

    @Test
    public void quickStartExample() {
        // Instantiates a client
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    // The kind for the new entity
    String kind = "Task";
    // The name/ID for the new entity
    String name = "sampletask1";
    // The Cloud Datastore key for the new entity
    Key taskKey = datastore.newKeyFactory().setKind(kind).newKey(name);

    // Prepares the new entity
    Entity task = Entity.newBuilder(taskKey)
        .set("description", "Buy milk")
        .build();

    // Saves the entity
    datastore.put(task);

    System.out.printf("Saved %s: %s%n", task.getKey().getName(), task.getString("description"));

    //Retrieve entity
    Entity retrieved = datastore.get(taskKey);

    System.out.printf("Retrieved %s: %s%n", taskKey.getName(), retrieved.getString("description"));

    }
}
