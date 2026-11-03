
package com.tcf.feereceiptservice.service;

import java.util.List;

import com.tcf.feereceiptservice.dto.ReceiptRequest;
import com.tcf.feereceiptservice.dto.ReceiptUpdateRequest;
import com.tcf.feereceiptservice.entity.FeeReceipt;

public interface IFeeReceiptService {

    FeeReceipt generateReceipt(ReceiptRequest request);

    List<FeeReceipt> getAllReceipts();

    FeeReceipt getReceiptById(Long id);

    FeeReceipt getReceiptByNumber(String receiptNumber);

    List<FeeReceipt> getReceiptsByStudent(Long studentId);

    FeeReceipt updateReceipt(Long id, ReceiptUpdateRequest request);

    void deleteReceipt(Long id);
}
