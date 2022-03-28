/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.app.mediafilter;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.ReaderInputStream;
import org.apache.tika.Tika;
import org.dspace.content.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Extract flat text from Microsoft Word documents (.doc, .docx).
 */
public class PoiWordFilter
    extends MediaFilter {
    private static final Logger LOG = LoggerFactory.getLogger(PoiWordFilter.class);

    @Override
    public String getFilteredName(String oldFilename) {
        return oldFilename + ".txt";
    }

    @Override
    public String getBundleName() {
        return "TEXT";
    }

    @Override
    public String getFormatString() {
        return "Text";
    }

    @Override
    public String getDescription() {
        return "Extracted text";
    }

    @Override
    public InputStream getDestinationStream(Item currentItem, InputStream source, boolean verbose)
        throws Exception {
        InputStream textStream;
        // Use Tika to parse text from input
        try (Reader reader = new Tika().parse(source)) {
            textStream = new ReaderInputStream(reader, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.format("Invalid File Format:  %s%n", e.getMessage());
            LOG.error("Unable to parse the bitstream:  ", e);
            throw e;
        }

        // if verbose flag is set, print out extracted text to STDOUT
        if (verbose) {
            System.out.println(IOUtils.toString(textStream, StandardCharsets.UTF_8));
        }

        // return the extracted text as a stream.
        return textStream;
    }
}
