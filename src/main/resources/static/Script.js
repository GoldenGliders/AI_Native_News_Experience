// ===== Dark Mode Toggle =====
const toggleBtn = document.getElementById('toggleDark');

if (localStorage.getItem('darkMode') === 'enabled') {
    document.body.classList.add('dark');
    toggleBtn.textContent = '☀️';
} else {
    toggleBtn.textContent = '🌙';
}

toggleBtn.addEventListener('click', () => {
    document.body.classList.toggle('dark');
    if (document.body.classList.contains('dark')) {
        localStorage.setItem('darkMode', 'enabled');
        toggleBtn.textContent = '☀️';
    } else {
        localStorage.setItem('darkMode', 'disabled');
        toggleBtn.textContent = '🌙';
    }
});

// ===== Load News Dynamically =====
async function loadNews() {
    const container = document.getElementById("newsContainer");
    const select = document.getElementById("newsSelect");

    try {
        const res = await fetch("/api/news");
        const newsList = await res.json();

        container.innerHTML = "";
        select.innerHTML = "";

        newsList.forEach((news, index) => {
            // News Card
            const card = document.createElement("div");
            card.className = "card news-card";
            card.innerHTML = `
                <h3>${news.title}</h3>
                <p>${news.content}</p>
                <p><strong>Category:</strong> ${news.category} | <strong>Date:</strong> ${news.date}</p>
                <div class="news-buttons">
                    <button onclick="summarizeNews(${index}, this)">Summarize</button>
                    <button onclick="explainNews(${index}, this)">Explain Simply</button>
                    <button onclick="loadFlashcards(${index})">Flashcards</button>
                </div>
                <div class="news-output"></div>
            `;
            container.appendChild(card);

            // Add to select dropdown
            const option = document.createElement("option");
            option.value = index;
            option.text = news.title;
            select.appendChild(option);
        });
    } catch (err) {
        console.error(err);
    }
}

// ===== Summarize News Inline =====
async function summarizeNews(index, btn) {
    const card = btn.closest(".news-card");
    const output = card.querySelector(".news-output");

    try {
        const res = await fetch(`/api/summarize/${index}`);
        const summary = await res.text();
        output.innerHTML = `<strong>Summary:</strong> ${summary}`;
    } catch (err) {
        console.error(err);
        output.innerHTML = `<strong>Error summarizing news</strong>`;
    }
}

// ===== Explain News Simply Inline =====
async function explainNews(index, btn) {
    const card = btn.closest(".news-card");
    const output = card.querySelector(".news-output");

    try {
        const res = await fetch(`/api/explain/${index}`);
        const explanation = await res.text();
        output.innerHTML = `<strong>Explain Simply:</strong> ${explanation}`;
    } catch (err) {
        console.error(err);
        output.innerHTML = `<strong>Error explaining news</strong>`;
    }
}

// ===== Flashcards Section =====
async function loadFlashcards(index) {
    const container = document.getElementById("flashcardsContainer");

    try {
        const res = await fetch(`/api/flashcards/${index}`);
        const cards = await res.json();

        container.innerHTML = "";
        cards.forEach(card => {
            const div = document.createElement("div");
            div.className = "card";
            div.innerHTML = `<h4>${card.question}</h4><p>${card.answer}</p>`;
            container.appendChild(div);
        });
    } catch (err) {
        console.error(err);
    }
}

// ===== Ask a Question =====
async function askQuestion() {
    const questionInput = document.getElementById("questionInput");
    const answerBox = document.getElementById("answerBox");
    const select = document.getElementById("newsSelect");

    const question = questionInput.value.trim();
    const index = select.value;

    if (!question) return alert("Please enter a question");

    try {
        const res = await fetch(`/api/ask/${index}?question=${encodeURIComponent(question)}`);
        const answer = await res.text();
        answerBox.innerHTML = `<strong>Answer:</strong> ${answer}`;

        // Load flashcards automatically
        loadFlashcards(index);
    } catch (err) {
        console.error(err);
        answerBox.innerHTML = `<strong>Error fetching answer</strong>`;
    }
}

// ===== Initialize =====
window.onload = loadNews;