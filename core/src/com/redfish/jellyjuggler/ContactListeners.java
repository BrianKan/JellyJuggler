package com.redfish.jellyjuggler;

/**
 * Created by Redfish on 7/17/2017.
 */

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactListeners implements ContactListener {

    private Model parent;

    public ContactListeners(Model parent){
        this.parent = parent;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();

        if(fa.getBody().getUserData()=="hello"){
            parent.gameOver=true;
            System.out.println("LET THE BODY HIT THE FLOOR");
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

}