package com.xdesign.cake.contents.retriever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CodeRetriever {
	public String retrieveCodeFor( String uri ) throws IOException {
		Writer writer = new StringWriter();
		char[] buffer = new char[2048];

		final URL url = new URL( uri );
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		try ( InputStream inputStream = httpURLConnection.getInputStream() ) {

			Reader reader = new BufferedReader(
					new InputStreamReader( inputStream, "UTF-8" ) );
			int counter;
			while ( ( counter = reader.read( buffer ) ) != -1 ) {
				writer.write( buffer, 0, counter );
			}

		} catch ( Exception e ) {
			log.info( "Error retrieving github source code : " + e.getMessage() );
		}
		return writer.toString();

	}
}
