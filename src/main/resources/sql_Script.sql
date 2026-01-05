use library_management_schema;


INSERT INTO books (book_title, book_author, isbn, category, available, added_at) VALUES
('To Kill a Mockingbird','Harper Lee','9780061120084','DRAMA',TRUE,NOW()),
('1984','George Orwell','9780451524935','SCIENCE_FICTION',TRUE,NOW()),
('Pride and Prejudice','Jane Austen','9780141439518','ROMANCE',TRUE,NOW()),
('The Great Gatsby','F. Scott Fitzgerald','9780743273565','DRAMA',TRUE,NOW()),
('The Catcher in the Rye','J.D. Salinger','9780316769488','DRAMA',TRUE,NOW()),
('The Hobbit','J.R.R. Tolkien','9780547928227','FANTASY',TRUE,NOW()),
('The Da Vinci Code','Dan Brown','9780307474279','MYSTERY',TRUE,NOW()),
('Harry Potter and the Sorcerer''s Stone','J.K. Rowling','9780590353428','FANTASY',TRUE,NOW()),
('The Alchemist','Paulo Coelho','9780061122416','DRAMA',TRUE,NOW()),
('The Silent Patient','Alex Michaelides','9781250301698','THRILLER',TRUE,NOW()),
('Brave New World','Aldous Huxley','9780060850525','SCIENCE_FICTION',TRUE,NOW()),
('The Girl on the Train','Paula Hawkins','9781594634025','THRILLER',TRUE,NOW()),
('The Fault in Our Stars','John Green','9780525478813','ROMANCE',TRUE,NOW()),
('Gone Girl','Gillian Flynn','9780307588372','THRILLER',TRUE,NOW()),
('Moby Dick','Herman Melville','9781503280787','DRAMA',TRUE,NOW()),
('The Lord of the Rings','J.R.R. Tolkien','9780618640158','FANTASY',TRUE,NOW()),
('The Kite Runner','Khaled Hosseini','9781594631932','DRAMA',TRUE,NOW()),
('A Game of Thrones','George R.R. Martin','9780553593717','FANTASY',TRUE,NOW()),
('Sapiens: A Brief History of Humankind','Yuval Noah Harari','9780062316098','SCIENCE_TECHNOLOGY',TRUE,NOW()),
('Educated','Tara Westover','9780399590505','BIOGRAPHY',TRUE,NOW()),
('The Book Thief','Markus Zusak','9780375842208','DRAMA',TRUE,NOW()),
('Thinking, Fast and Slow','Daniel Kahneman','9780374533558','SCIENCE_TECHNOLOGY',TRUE,NOW()),
('The Hunger Games','Suzanne Collins','9780439023482','FANTASY',TRUE,NOW()),
('Catching Fire','Suzanne Collins','9780439023499','FANTASY',TRUE,NOW()),
('Mockingjay','Suzanne Collins','9780439023512','FANTASY',TRUE,NOW()),
('The Martian','Andy Weir','9780553418027','SCIENCE_FICTION',TRUE,NOW()),
('The Chronicles of Narnia','C.S. Lewis','9780066238501','FANTASY',TRUE,NOW()),
('Life of Pi','Yann Martel','9780156027329','DRAMA',TRUE,NOW()),
('The Shining','Stephen King','9780307743658','HORROR',TRUE,NOW()),
('It','Stephen King','9781501142971','HORROR',TRUE,NOW()),
('Misery','Stephen King','9780450417400','HORROR',TRUE,NOW()),
('Dracula','Bram Stoker','9780486411096','HORROR',TRUE,NOW()),
('Frankenstein','Mary Shelley','9780486282115','HORROR',TRUE,NOW()),
('The Odyssey','Homer','9780140268868','ADVENTURE',TRUE,NOW()),
('War and Peace','Leo Tolstoy','9781400079989','HISTORICAL_FICTION',TRUE,NOW()),
('Anna Karenina','Leo Tolstoy','9780143035009','ROMANCE',TRUE,NOW()),
('Crime and Punishment','Fyodor Dostoevsky','9780143058145','DRAMA',TRUE,NOW()),
('The Brothers Karamazov','Fyodor Dostoevsky','9780374528380','DRAMA',TRUE,NOW()),
('Jane Eyre','Charlotte Brontë','9780142437210','ROMANCE',TRUE,NOW()),
('Wuthering Heights','Emily Brontë','9780141439557','ROMANCE',TRUE,NOW()),
('Little Women','Louisa May Alcott','9780142408767','ROMANCE',TRUE,NOW()),
('The Grapes of Wrath','John Steinbeck','9780143039434','DRAMA',TRUE,NOW()),
('Of Mice and Men','John Steinbeck','9780140177399','DRAMA',TRUE,NOW()),
('East of Eden','John Steinbeck','9780142004235','DRAMA',TRUE,NOW()),
('Fahrenheit 451','Ray Bradbury','9781451673320','SCIENCE_FICTION',TRUE,NOW()),
('Dune','Frank Herbert','9780441172720','FANTASY',TRUE,NOW()),
('The Name of the Wind','Patrick Rothfuss','9780756404742','FANTASY',TRUE,NOW()),
('The Wise Man''s Fear','Patrick Rothfuss','9780756407920','FANTASY',TRUE,NOW()),
('The Catch-22','Joseph Heller','9781451626651','DRAMA',TRUE,NOW()),
('Slaughterhouse-Five','Kurt Vonnegut','9780440180297','SCIENCE_FICTION',TRUE,NOW()),
('The Road','Cormac McCarthy','9780307387900','DRAMA',TRUE,NOW()),
('No Country for Old Men','Cormac McCarthy','9780307269752','THRILLER',TRUE,NOW()),
('Blood Meridian','Cormac McCarthy','9780679728758','DRAMA',TRUE,NOW()),
('The Handmaid''s Tale','Margaret Atwood','9780385490819','SCIENCE_FICTION',TRUE,NOW()),
('Oryx and Crake','Margaret Atwood','9780385721674','SCIENCE_FICTION',TRUE,NOW()),
('The Blind Assassin','Margaret Atwood','9780385721675','SCIENCE_FICTION',TRUE,NOW()),
('Beloved','Toni Morrison','9781400033417','DRAMA',TRUE,NOW()),
('Song of Solomon','Toni Morrison','9781400033424','DRAMA',TRUE,NOW()),
('The Color Purple','Alice Walker','9780156028357','DRAMA',TRUE,NOW()),
('Gone with the Wind','Margaret Mitchell','9781451635622','ROMANCE',TRUE,NOW()),
('The Help','Kathryn Stockett','9780399155346','DRAMA',TRUE,NOW()),
('Memoirs of a Geisha','Arthur Golden','9780679781586','ROMANCE',TRUE,NOW()),
('The Pillars of the Earth','Ken Follett','9780451166892','HISTORICAL_FICTION',TRUE,NOW()),
('World Without End','Ken Follett','9780451166893','HISTORICAL_FICTION',TRUE,NOW()),
('A Tale of Two Cities','Charles Dickens','9780486417770','HISTORICAL_FICTION',TRUE,NOW()),
('Great Expectations','Charles Dickens','9780141439564','DRAMA',TRUE,NOW()),
('David Copperfield','Charles Dickens','9780140439442','DRAMA',TRUE,NOW()),
('Oliver Twist','Charles Dickens','9780141439748','DRAMA',TRUE,NOW()),
('Les Misérables','Victor Hugo','9780451419440','HISTORICAL_FICTION',TRUE,NOW()),
('The Count of Monte Cristo','Alexandre Dumas','9780140449268','ADVENTURE',TRUE,NOW()),
('The Three Musketeers','Alexandre Dumas','9780140449269','ADVENTURE',TRUE,NOW()),
('Around the World in Eighty Days','Jules Verne','9781503215154','ADVENTURE',TRUE,NOW()),
('Journey to the Center of the Earth','Jules Verne','9781512094508','ADVENTURE',TRUE,NOW()),
('Twenty Thousand Leagues Under the Sea','Jules Verne','9781512094515','ADVENTURE',TRUE,NOW()),
('The Time Machine','H.G. Wells','9781512094522','SCIENCE_FICTION',TRUE,NOW()),
('The Invisible Man','H.G. Wells','9781512094539','SCIENCE_FICTION',TRUE,NOW()),
('War of the Worlds','H.G. Wells','9781512094546','SCIENCE_FICTION',TRUE,NOW()),
('The Art of War','Sun Tzu','9781590302256','HISTORY',TRUE,NOW()),
('Meditations','Marcus Aurelius','9780486298239','PHILOSOPHY',TRUE,NOW());


 
 select * from users;
 select * from books;
 select * from borrow_records;
 select * from reservation;
 
SET FOREIGN_KEY_CHECKS = 0;

drop table books;
drop table reservation;
drop table borrow_records;

TRUNCATE TABLE users;
TRUNCATE TABLE books;
TRUNCATE TABLE borrow_records;
TRUNCATE TABLE reservation;
TRUNCATE TABLE users_books;
TRUNCATE TABLE users_borrow_records;
TRUNCATE TABLE users_reservation;
SET FOREIGN_KEY_CHECKS = 1;


ALTER TABLE books DROP COLUMN available_quantity;
ALTER TABLE books DROP COLUMN total_quantity;

alter table library_management_schema.users Auto_increment=1;
alter table library_management_schema.books Auto_increment=1;
alter table library_management_schema.borrow_records Auto_increment=1;
alter table library_management_schema.reservation Auto_increment=1;
alter table library_management_schema.users_books Auto_increment=1;
alter table library_management_schema.users_borrow_records Auto_increment=1;
alter table library_management_schema.users_reservation Auto_increment=1;
 