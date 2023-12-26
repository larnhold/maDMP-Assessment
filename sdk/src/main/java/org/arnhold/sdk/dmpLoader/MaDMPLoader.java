package org.arnhold.sdk.dmpLoader;

import org.apache.jena.rdf.model.Model;

import java.io.File;

public interface MaDMPLoader {

    Model fromIdentifier(File identifier);

}
