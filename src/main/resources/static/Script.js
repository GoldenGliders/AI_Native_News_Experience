// DARK MODE TOGGLE
const toggleBtn = document.getElementById("toggleDark");

toggleBtn.addEventListener("click", () => {
  document.body.classList.toggle("dark");

  // Change icon
  if (document.body.classList.contains("dark")) {
    toggleBtn.innerText = "☀️";
  } else {
    toggleBtn.innerText = "🌙";
  }
});


// ASK QUESTION FUNCTION (Gemini API backend call)
async function askQuestion() {
  const questionInput = document.getElementById("questionInput");
  const answerBox = document.getElementById("answerBox");
  const newsIdInput = document.getElementById("newsIdInput");

  const question = questionInput.value.trim();
  const id = newsIdInput.value.trim();

  if (!id) {
    alert("Please enter News ID");
    return;
  }

  if (!question) {
    alert("Please enter a question");
    return;
  }

  answerBox.innerText = "⏳ Thinking... Please wait";

  try {
    const response = await fetch(`/api/news/question/${id}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        question: question
      })
    });

    if (!response.ok) {
      throw new Error("Server error: " + response.status);
    }

    const data = await response.text();

    answerBox.innerText = data;
  } catch (error) {
    console.error(error);
    answerBox.innerText = "❌ Error: Unable to get AI response.";
  }
}