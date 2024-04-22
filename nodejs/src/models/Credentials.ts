import Realm from "realm";

// Path: nodejs/src/models/Credentials.ts

// Define the Credentials schema

class Credentials extends Realm.Object {
  
    static schema = {
      name: "Credentials",
      properties: {
        username: { type: "string", indexed: true },
        email: { type: "string", indexed: true, unique: true },
        password: "string",
        salt: "string",
      },
      primaryKey: "username",
    };
  }
  