package org.arnhold.plugins;

import org.apache.jena.rdf.model.Model;
import org.example.dcsojson.DcsoJsonTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;

@Component
@Service
public class MaDMPFileLoaderImpl implements MaDMPLoader {

    @Autowired
    DcsoJsonTransformer dcsoJsonTransformer;

    @Override
    public Model fromIdentifier(File identifier) {
        try {
            return dcsoJsonTransformer.convertPlainToModel(identifier);
        } catch (Exception e) {
            // TODO replace with proper error handling
            throw new RuntimeException(e);
        }
    }
}
