package com.example.activitea.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.activitea.Dto.CoverLetterDto;
import com.example.activitea.entity.CoverLetter;
import com.example.activitea.service.CoverLetterService;
import com.example.activitea.service.PdfService;
import com.itextpdf.text.DocumentException;

@RestController
@CrossOrigin("http://localhost:3000")
public class CoverLetterController {

	@Autowired
	private CoverLetterService coverLetterService;
	@Autowired
	private PdfService pdfService;
	
		//Crud create a new coverLetter
		@PostMapping("/coverletter")
		public ResponseEntity<String> create(@RequestBody CoverLetterDto coverLetterDto){
			if (coverLetterService.create(coverLetterDto)) {
				return new ResponseEntity<String>("L'annonce a bien été enregistrée",HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>("oups, une erreur s'est produite",HttpStatus.BAD_REQUEST);
			}
		}

		//Crud Read the user coverletter to select one 
		@GetMapping("/coverletter/{id}")
		public List<CoverLetterDto> readCoverLetter(@PathVariable("id") int userId){
			return  coverLetterService.readByUserId(userId);
		}
			
		// Find a coverletter by id before update it or other possible action
		@GetMapping("/readcoverletter/{id}") 
		public CoverLetterDto findCoverLetterById(@PathVariable("id") int coverLetterId){
			return  coverLetterService.findById(coverLetterId);
		}
		
		// find the last coverletter created 
	    @GetMapping("/last-coverletter/{id}")
	    public ResponseEntity<CoverLetter> getLastCoverLetter(@PathVariable("id") int userId) {
	        CoverLetter lastCoverLetter = coverLetterService.getLastCoverLetter(userId);
	        if (lastCoverLetter != null) {
	            return ResponseEntity.ok(lastCoverLetter);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
		
		//Crud update the coverletter
		@PutMapping("/coverletter/{id}")
		public ResponseEntity<String> updateCoverLetter(@PathVariable("id") int coverLetterId, @RequestBody CoverLetterDto coverLetterDto){
			// Define a directory for storing generated PDFs
			String pdfStorageDirectory = "/path/to/pdf/storage";
			try {
				byte[] pdfBytes = pdfService.createPDFFromCoverLetter(coverLetterDto.getLetter());
				
				// Get current date and time
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
				String formattedDateTime = now.format(formatter);
				String fileName = "coverletter"+coverLetterId+"_"+formattedDateTime+".pdf";
				String filePath = pdfStorageDirectory+File.separator+fileName;
				pdfService.savePDFToFile(pdfBytes,filePath);
				
			} catch (DocumentException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (coverLetterService.updateCoverLetter(coverLetterId, coverLetterDto)) {
				
				 return new ResponseEntity<>("La letter de motivation a bien été mis à jour", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>("La letter de motivation n'a pas pu être mis à jour", HttpStatus.BAD_REQUEST);
			}
		}
		
		//Crud Delete the selected coverletter from the user account
		@DeleteMapping("/coverletter/{id}")
		public ResponseEntity<String> deleteCoverletter(@PathVariable("id") int coverLetterId){
			if (coverLetterService.deleteCoverLetter(coverLetterId)) {
				return new ResponseEntity<String>("La lettre de motivation a bien été supprimée",HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>("La lettre de motivation n'a pas pu être supprimée",HttpStatus.BAD_REQUEST);
			}
		}
		
		@GetMapping(value = "/createpdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
		public ResponseEntity<Resource> generatePDF(@PathVariable("id") int coverLetterId) {
			CoverLetterDto coverletterDto = coverLetterService.findById(coverLetterId);
				System.err.println(coverLetterId);
				System.err.println(coverletterDto.getLetter());
		    try {
		        byte[] pdfBytes = pdfService.createPDFFromCoverLetter(coverletterDto.getLetter());

		        HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.APPLICATION_PDF);
		        headers.setContentDispositionFormData("attachment", "coverletter.pdf");

		        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

		        return ResponseEntity.ok()
		            .headers(headers)
		            .contentLength(pdfBytes.length)
		            .body(resource);
		    } catch (IOException e) {
		        // Handle PDF generation errors
		        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    } catch (DocumentException e) {
		        // Handle iText DocumentException
		        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		}
		
}
