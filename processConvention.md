# Quy trình Review Pull Request trên GitHub

## **1. Tạo Pull Request**
- **Người tạo PR**:
  1. Đảm bảo nhánh code đã được cập nhật từ nhánh chính (main hoặc develop).
  2. Viết tiêu đề và mô tả rõ ràng cho PR, bao gồm:
     - **Mục đích của PR**.
     - **Các thay đổi chính** (code, logic, tính năng mới, sửa lỗi, v.v.).
     - **Cách kiểm tra PR** (steps to test).
  3. Gắn nhãn phù hợp cho PR (feature, bugfix, hotfix, v.v.).
  4. Gán reviewer (người review).

---

## **2. Quy trình Review PR**
- **Reviewer**:
  1. **Hiểu mục tiêu của PR**:
     - Đọc tiêu đề và mô tả PR.
     - Kiểm tra tài liệu liên quan nếu cần (Jira, Notion, v.v.).
  2. **Kiểm tra tổng quát**:
     - Xác nhận PR có đáp ứng mục tiêu hay không.
     - Kiểm tra xem code có ảnh hưởng đến các phần khác của hệ thống.
  3. **Kiểm tra chi tiết**:
     - **Code quality**:
       - Tuân thủ coding conventions (naming, format, comment).
       - Code có thể tối ưu hơn không?
       - Có xuất hiện **anti-pattern** hoặc **code smell** không?
     - **Logic**:
       - Logic xử lý có đúng như yêu cầu không?
       - Đã xử lý hết các trường hợp edge cases chưa?
     - **Performance**:
       - Code có ảnh hưởng đến hiệu suất không?
       - Có cần tối ưu không?
     - **Bảo mật**:
       - Có lỗ hổng bảo mật nào không?
       - Input validation đã được xử lý chưa?
     - **Test**:
       - Đã có unit test hoặc test case cụ thể chưa?
       - Kết quả test có đáp ứng mong đợi không?
  4. **Góp ý và cải thiện**:
     - Đưa ra feedback rõ ràng và mang tính xây dựng.
     - Ghi chú cụ thể từng dòng hoặc khối code cần cải thiện.
  5. **Approved hoặc Request Changes**:
     - Nếu code đạt yêu cầu, bấm **Approve**.
     - Nếu cần chỉnh sửa, bấm **Request Changes** và ghi rõ yêu cầu.

---

## **3. Người Tạo PR Xử Lý Feedback**
- **Người tạo PR**:
  1. Đọc và hiểu feedback.
  2. Chỉnh sửa code theo góp ý.
  3. Comment trả lời reviewer nếu cần giải thích.
  4. Push code mới lên, sau đó nhấn **Re-request review**.

---

## **4. Merge Pull Request**
- **Người tạo PR hoặc reviewer**:
  1. Đảm bảo mọi feedback đã được giải quyết.
  2. Kiểm tra lại code lần cuối.
  3. Đảm bảo CI/CD chạy thành công (nếu có).
  4. Merge PR:
     - **Squash and merge**: gộp tất cả commit thành một.
     - **Merge commit**: giữ nguyên các commit.
     - **Rebase and merge**: rebase và merge (tuỳ team).
  5. Xóa nhánh sau khi merge (nếu không cần dùng nữa).

---

## **5. Retrospective**
- Định kỳ review quy trình PR trong nhóm để cải thiện.
- Chia sẻ feedback hoặc các lỗi thường gặp trong các buổi họp nhóm.
