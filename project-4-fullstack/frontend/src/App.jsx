import { useState, useEffect } from 'react'

function App() {
  const [books, setBooks] = useState([])
  const [title, setTitle] = useState('')
  const [author, setAuthor] = useState('')

  const fetchBooks = () => {
    fetch('/api/books')
      .then(res => res.json())
      .then(data => setBooks(data))
      .catch(err => console.error('Failed to fetch books:', err))
  }

  useEffect(() => {
    fetchBooks()
  }, [])

  const addBook = async (e) => {
    e.preventDefault()
    await fetch('/api/books', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ title, author })
    })
    setTitle('')
    setAuthor('')
    fetchBooks()
  }

  return (
    <div style={{ padding: '2rem', fontFamily: 'sans-serif' }}>
      <h1>Book Corner (Project 4 Demo)</h1>

      <form onSubmit={addBook} style={{ marginBottom: '1.5rem' }}>
        <input
          placeholder="Title"
          value={title}
          onChange={e => setTitle(e.target.value)}
          required
        />
        <input
          placeholder="Author"
          value={author}
          onChange={e => setAuthor(e.target.value)}
          required
          style={{ marginLeft: '0.5rem' }}
        />
        <button type="submit" style={{ marginLeft: '0.5rem' }}>Add Book</button>
      </form>

      <ul>
        {books.map(book => (
          <li key={book.id}>{book.title} — {book.author}</li>
        ))}
      </ul>
    </div>
  )
}

export default App