import pytest
from score_processor import ScoreProcessor


def test_process_score_file_success(tmp_path):
    file_path = tmp_path / "score.txt"
    file_path.write_text("a")

    processor = ScoreProcessor()
    result = processor.process_score_file(str(file_path))

    assert result == 120


def test_process_score_file_missing_file():
    processor = ScoreProcessor()

    with pytest.raises(FileNotFoundError):
        processor.process_score_file("missing_file.txt")


def test_process_score_file_invalid_data(tmp_path):
    file_path = tmp_path / "bad_score.txt"
    file_path.write_text("abc")

    processor = ScoreProcessor()

    with pytest.raises(ValueError):
        processor.process_score_file(str(file_path))
