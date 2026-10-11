const starterBooks = [
  {id:1,title:"The Midnight Library",author:"Matt Haig",category:"Fiction",description:"Between life and death there is a library, and within it, a chance to try another life.",cover:"THE MIDNIGHT LIBRARY",rating:4.7},
  {id:2,title:"Atomic Habits",author:"James Clear",category:"Self-growth",description:"Small habits, remarkable results: a practical guide to getting 1% better every day.",cover:"ATOMIC HABITS",rating:4.8},
  {id:3,title:"The Alchemist",author:"Paulo Coelho",category:"Adventure",description:"A shepherd's journey becomes a timeless story about dreams and listening to your heart.",cover:"THE ALCHEMIST",rating:4.6},
  {id:4,title:"Ikigai",author:"Héctor García & Francesc Miralles",category:"Wellness",description:"A gentle exploration of purpose, daily rituals, and the joy of finding your reason to get up.",cover:"IKIGAI",rating:4.4},
  {id:5,title:"Educated",author:"Tara Westover",category:"Memoir",description:"A powerful memoir about education, identity, and the courage to reshape your own story.",cover:"EDUCATED",rating:4.5},
  {id:6,title:"Deep Work",author:"Cal Newport",category:"Productivity",description:"Learn to protect your focus and do meaningful work in a world full of distractions.",cover:"DEEP WORK",rating:4.3}
];
const starterReviews = [
  {id:1,name:"Maya R.",bookId:1,rating:5,comment:"A beautiful reminder that every small choice can shape a different future. I kept thinking about it long after finishing.",date:"Reader favorite"},
  {id:2,name:"Arjun K.",bookId:2,rating:5,comment:"Practical and easy to return to. The tiny-habit idea made personal growth feel much more manageable.",date:"Shared this week"},
  {id:3,name:"Leah S.",bookId:3,rating:4,comment:"Simple, hopeful, and full of lines worth highlighting. A lovely book to read slowly and reflect on.",date:"Community pick"}
];
const load = (key, fallback) => { try { const value = localStorage.getItem(key); return value ? JSON.parse(value) : fallback; } catch { return fallback; } };
let books = load("booknest-books", starterBooks);
let reviews = load("booknest-reviews", starterReviews);
const save = () => { localStorage.setItem("booknest-books", JSON.stringify(books)); localStorage.setItem("booknest-reviews", JSON.stringify(reviews)); };
const average = id => { const matches = reviews.filter(r => r.bookId === id); return matches.length ? matches.reduce((sum,r)=>sum + Number(r.rating),0)/matches.length : null; };
const escapeHTML = value => String(value).replace(/[&<>"']/g, c => ({"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#39;"}[c]));
function renderBooks() {
  const query = document.getElementById("searchInput").value.trim().toLowerCase();
  const sort = document.getElementById("sortSelect").value;
  let shown = books.filter(b => `${b.title} ${b.author} ${b.category}`.toLowerCase().includes(query));
  if (sort === "title") shown.sort((a,b)=>a.title.localeCompare(b.title));
  if (sort === "rating") shown.sort((a,b)=>(average(b.id) ?? b.rating)-(average(a.id) ?? a.rating));
  const grid = document.getElementById("bookGrid");
  grid.innerHTML = shown.length ? shown.map((b,index) => {
    const userRating = average(b.id);
    const rating = userRating === null ? b.rating : userRating;
    const count = reviews.filter(r=>r.bookId===b.id).length;
    return `<article class="book-card" style="animation-delay:${index*45}ms"><div class="cover cover-${((b.id-1)%6)+1}"><span>${escapeHTML(b.cover || b.title.toUpperCase())}</span><small>BOOKNEST EDITION</small></div><div class="card-top"><span class="tag">${escapeHTML(b.category)}</span><span class="rating">★ ${Number(rating).toFixed(1)}</span></div><h3>${escapeHTML(b.title)}</h3><p class="author">by ${escapeHTML(b.author)}</p><p class="book-desc">${escapeHTML(b.description || "A reader-recommended title to add to your list.")}</p><div class="card-bottom"><span>${count} community review${count===1?"":"s"}</span><button type="button" data-review-book="${b.id}">Review this ↗</button></div></article>`;
  }).join("") : `<p class="empty-state">No books match that search. Try another title or author.</p>`;
  document.getElementById("bookCount").textContent = books.length;
  document.getElementById("reviewCount").textContent = reviews.length;
  const bookSelect = document.getElementById("reviewBook");
  const previous = bookSelect.value;
  bookSelect.innerHTML = books.map(b=>`<option value="${b.id}">${escapeHTML(b.title)}</option>`).join("");
  if (books.some(b=>String(b.id)===previous)) bookSelect.value=previous;
}
function renderReviews() {
  const list = document.getElementById("reviewList");
  const latest = [...reviews].sort((a,b)=>b.id-a.id).slice(0,6);
  list.innerHTML = latest.length ? latest.map(r=>{
    const book = books.find(b=>b.id===r.bookId);
    return `<article class="review-card"><div class="review-stars" aria-label="${r.rating} out of 5 stars">${"★".repeat(r.rating)}${"☆".repeat(5-r.rating)}</div><blockquote>“${escapeHTML(r.comment)}”</blockquote><div class="review-person"><span class="avatar">${escapeHTML(r.name.trim().charAt(0).toUpperCase() || "R")}</span><div><strong>${escapeHTML(r.name)}</strong><small>${escapeHTML(book ? book.title : "Book review")} · ${escapeHTML(r.date || "Community review")}</small></div></div></article>`;
  }).join("") : `<p class="empty-state">Be the first to share a review.</p>`;
}
document.getElementById("searchInput").addEventListener("input",renderBooks);
document.getElementById("sortSelect").addEventListener("change",renderBooks);
document.getElementById("bookGrid").addEventListener("click",event=>{
  const button=event.target.closest("[data-review-book]");
  if(button){document.getElementById("reviewBook").value=button.dataset.reviewBook;document.getElementById("add-review").scrollIntoView({behavior:"smooth"});}
});
document.getElementById("reviewForm").addEventListener("submit",event=>{
  event.preventDefault();
  const name=document.getElementById("reviewerName").value.trim();
  const bookId=Number(document.getElementById("reviewBook").value);
  const rating=Number(document.getElementById("reviewRating").value);
  const comment=document.getElementById("reviewComment").value.trim();
  if(!name||!books.some(b=>b.id===bookId)||rating<1||rating>5||!comment){document.getElementById("formMessage").textContent="Please complete every field before publishing.";return;}
  reviews.push({id:Date.now(),name,bookId,rating,comment,date:"Just now"});
  save();renderBooks();renderReviews();event.target.reset();
  document.getElementById("formMessage").textContent="Your review has been published in this browser. Thank you!";
});
document.getElementById("resetDemo").addEventListener("click",()=>{
  if(confirm("Reset the demo to its original sample books and reviews?")){
    books=starterBooks.map(b=>({...b}));reviews=starterReviews.map(r=>({...r}));
    save();renderBooks();renderReviews();document.getElementById("formMessage").textContent="Demo data reset.";
  }
});
renderBooks();renderReviews();
